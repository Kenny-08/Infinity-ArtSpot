package com.ken.infinity.controllers;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.User;
import com.ken.infinity.repository.UserRepository;
import com.ken.infinity.services.ArtworkService;
import com.ken.infinity.services.SecurityService;
import com.ken.infinity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import java.security.Principal;

@Controller
public class AddArtController {

    public ArtworkService artworkService;
    public UserService userService;
    public SecurityService securityService;

    @Autowired
    public AddArtController(ArtworkService artworkService, UserService userService, SecurityService securityService) {
        this.artworkService = artworkService;
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/addArt")
    public String addArt(Model model){
        model.addAttribute("artwork", new Artwork());
        return "addArt";
    }

    @PostMapping("/addArt")
    public String addArt(@ModelAttribute("artwork") Artwork artwork, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException{

        model.addAttribute("loggedIn", securityService.isLoggedIn());
        int currentUserId;
        try{
            currentUserId = userService.findByUsername(securityService.findLoggedInUsername()).getId();
        }
        catch (Exception e){
            return "redirect:/login";
        }

        User user = userService.findByUserId(currentUserId);
        System.out.println("current user id" + currentUserId);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        artwork.setImgUrl(fileName);
        artworkService.save(artwork, user);

        String uploadDir = "src/main/resources/static/img/artwork-photos/" + artwork.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/homepage";
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
