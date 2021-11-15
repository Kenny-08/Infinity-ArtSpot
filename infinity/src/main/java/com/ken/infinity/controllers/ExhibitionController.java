package com.ken.infinity.controllers;

import com.ken.infinity.models.*;
import com.ken.infinity.services.ExhibitionRegisterService;
import com.ken.infinity.services.ExhibitionService;
import com.ken.infinity.services.SecurityService;
import com.ken.infinity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExhibitionController {

    @Autowired
    JavaMailSender javaMailSender;

    ExhibitionService exhibitionService;
    SecurityService securityService;
    UserService userService;
    ExhibitionRegisterService exhibitionRegisterService;

    @Autowired
    public ExhibitionController(ExhibitionService exhibitionService, SecurityService securityService, UserService userService, ExhibitionRegisterService exhibitionRegisterService) {
        this.exhibitionService = exhibitionService;
        this.securityService = securityService;
        this.userService = userService;
        this.exhibitionRegisterService = exhibitionRegisterService;
    }

    @GetMapping("/exhibition")
    public String workshop(Model model){
        List<Exhibition> exhibitions = exhibitionService.getExhibitions();
        model.addAttribute("exhibitions", exhibitions);

        return "exhibition";
    }

    @GetMapping("/heldExhibition")
    public String heldExhibition(Model model){
        model.addAttribute("exhibition", new Exhibition());
        return "heldExhibition";
    }

    @PostMapping("/heldExhibition")
    public String heldExhibition(@ModelAttribute("exhibition") Exhibition exhibition, Model model, @RequestParam("image") MultipartFile multipartFile, @RequestParam("localDatetime") String localDatetime) throws IOException {
        model.addAttribute("loggedIn", securityService.isLoggedIn());
        int currentUserId;
        try{
            currentUserId = userService.findByUsername(securityService.findLoggedInUsername()).getId();
        }
        catch (Exception e){
            return "redirect:/login";
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        exhibition.setImgUrl(fileName);

        System.out.println("Current datetime " + localDatetime);
        String datetimeTimestamp =localDatetime;
        datetimeTimestamp+= ":00";

        System.out.println(datetimeTimestamp);

        exhibition.setDatetime(Timestamp.valueOf(datetimeTimestamp.replace("T"," ")));


        exhibitionService.save(exhibition);


        String uploadDir = "src/main/resources/static/img/exhibition-photos/" + exhibition.getId();

        ExhibitionController.FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/exhibition";
    }

    @GetMapping("/singleExhibition")
    public String singleExhibition(){
        return "singleExhibition";
    }

    @RequestMapping("/exhibition/{exhibitionId}")
    public String ExhibitionById(Model model, @PathVariable("exhibitionId") int exhibitionId){
        model.addAttribute("loggedIn", securityService.isLoggedIn());
        Map<String, Object> map = new HashMap<String, Object>();

        Exhibition exhibition = exhibitionService.findExhibitionById(exhibitionId);

        map.put("exhibition", exhibition);

        ExhibitionRegister exhibitionRegister = new ExhibitionRegister();
        model.addAttribute(exhibitionRegister);

        model.addAllAttributes(map);

        if(securityService.isLoggedIn())

            return "singleExhibition";
        return "redirect:/login";
    }


    @RequestMapping(value = "/confirmExhibition", method = { RequestMethod.GET, RequestMethod.POST })
    public String RegisterExhibition(@ModelAttribute("exhibitionRegister")ExhibitionRegister exhibitionRegister, @RequestParam("exhibition_id") int exhibition_id, Model model){
        model.addAttribute("loggedIn", securityService.isLoggedIn());

        int currentUserId;
        try{
            currentUserId = userService.findByUsername(securityService.findLoggedInUsername()).getId();
        }
        catch (Exception e){
            return "redirect:/login";
        }

        User user = userService.findByUserId(currentUserId);
        System.out.println(user);
        System.out.println(exhibition_id);
        Exhibition exhibition = exhibitionService.findExhibitionById(exhibition_id);
        exhibitionService.updateExhibitionSeats(exhibition_id, exhibition.getRegistered_seats());

        exhibitionRegisterService.save(exhibitionRegister, user, exhibition);

//      start sending mail

        String from = "nairobi.sen.42@gmail.com";
        String to = user.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Seat Registered for Workshop");
        message.setText("Hello " + user.getFirstName() + "! \n" +
                "You have successfully registered for the Exhibition. "+
                "Hope to see you soon and have an amazing experience!" +
                "\n" +
                "\n" +
                "Sincerely, \n" +
                "Infinity Art Gallery");

        javaMailSender.send(message);


//        end sending mail


        return "redirect:/exhibition";


    }



    private class FileUploadUtil {
        public static void saveFile(String uploadDir, String fileName,
                                    MultipartFile multipartFile) throws IOException {
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + fileName, ioe);
            }
        }
    }
}
