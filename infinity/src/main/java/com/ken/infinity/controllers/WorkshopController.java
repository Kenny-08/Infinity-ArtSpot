package com.ken.infinity.controllers;

import com.ken.infinity.models.*;
import com.ken.infinity.services.SecurityService;
import com.ken.infinity.services.UserService;
import com.ken.infinity.services.WorkshopRegisterService;
import com.ken.infinity.services.WorkshopService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WorkshopController {

    SecurityService securityService;
    UserService userService;
    WorkshopService workshopService;
    WorkshopRegisterService workshopRegisterService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public WorkshopController(SecurityService securityService, UserService userService, WorkshopService workshopService, WorkshopRegisterService workshopRegisterService) {
        this.securityService = securityService;
        this.userService = userService;
        this.workshopService = workshopService;
        this.workshopRegisterService = workshopRegisterService;
    }

    @GetMapping("/workshop")
    public String workshop(Model model){
        List<Workshop> workshops = workshopService.getWorkshops();
        Map<Object, String> workshopAndOrganizer = new HashMap<>();
        for(Workshop workshop: workshops){
            workshopAndOrganizer.put(workshop, workshopService.getWorkshopOrganizerName(workshop));
        }
        model.addAttribute("workshops", workshops);
        model.addAttribute("workshopAndOrganizer", workshopAndOrganizer);

        return "workshop";
    }


    @GetMapping("/singleWorkshop")
    public String singleWorkshop(){
        return "singleWorkshop";
    }

    @GetMapping("/organizeWorkshop")
    public String organizeWorkshop(Model model){
        model.addAttribute("workshop", new Workshop());
        return "organizeWorkshop";
    }

    @PostMapping("/organizeWorkshop")
    public String organizeWorkshop(@ModelAttribute("workshop") Workshop workshop, Model model, @RequestParam("image") MultipartFile multipartFile, @RequestParam("localDatetime") String localDatetime) throws IOException {
        model.addAttribute("loggedIn", securityService.isLoggedIn());
        int currentUserId;
        try{
            currentUserId = userService.findByUsername(securityService.findLoggedInUsername()).getId();
        }
        catch (Exception e){
            return "redirect:/login";
        }
        User user = userService.findByUserId(currentUserId);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        workshop.setImgUrl(fileName);

        System.out.println("Current datetime " + localDatetime);
        String datetimeTimestamp =localDatetime;
        datetimeTimestamp+= ":00";

        System.out.println(datetimeTimestamp);

        workshop.setDatetime(Timestamp.valueOf(datetimeTimestamp.replace("T"," ")));


        workshopService.save(workshop, user);


        String uploadDir = "src/main/resources/static/img/workshop-photos/" + workshop.getId();

        WorkshopController.FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/workshop";
    }

    @RequestMapping("/workshop/{workshopId}")
    public String WorkshopById(Model model, @PathVariable("workshopId") int workshopId){
        model.addAttribute("loggedIn", securityService.isLoggedIn());
        Map<String, Object> map = new HashMap<String, Object>();
        Workshop workshop = workshopService.findWorkshopById(workshopId);
        map.put("workshop", workshop);

        WorkshopRegister workshopRegister = new WorkshopRegister();
        model.addAttribute(workshopRegister);


        model.addAttribute("organizer", workshopService.getWorkshopOrganizerName(workshop));
        model.addAllAttributes(map);

        if(securityService.isLoggedIn())

            return "singleWorkshop";
        return "redirect:/login";
    }


    @RequestMapping(value = "/confirm", method = { RequestMethod.GET, RequestMethod.POST })
    public String RegisterWorkshop(@ModelAttribute("workshopRegister")WorkshopRegister workshopRegister, @RequestParam("workshop_id") int workshop_id, Model model){
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
        System.out.println(workshop_id);
        Workshop workshop = workshopService.findWorkshopById(workshop_id);
        workshopService.updateWorkshopSeats(workshop_id, workshop.getRegistered_seats());

        workshopRegisterService.save(workshopRegister, user, workshop);

//      start sending mail

        String from = "nairobi.sen.42@gmail.com";
        String to = user.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Seat Registered for Workshop");
        message.setText("Hello " + user.getFirstName() + "! \n" +
                "You have successfully registered for the Workshop. "+
                "Hope to see you soon and have an amazing experience!" +
                "\n" +
                "\n" +
                "Sincerely, \n" +
                "Infinity Art Gallery");

        javaMailSender.send(message);


//        end sending mail

        if(securityService.isLoggedIn())
            return "redirect:/workshop";
        return "redirect:/login";

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

//TODO: Create conditions like if the seats are full, user can't register and if they have already registered, they can't register again