package com.ken.infinity.controllers;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.Workshop;
import com.ken.infinity.repository.ArtworkRepository;
import com.ken.infinity.services.ArtworkService;
import com.ken.infinity.services.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomepageController {

    ArtworkRepository artworkRepository;
    ArtworkService artworkService;
    WorkshopService workshopService;

    @Autowired
    public HomepageController(ArtworkRepository artworkRepository, ArtworkService artworkService, WorkshopService workshopService) {
        this.artworkRepository = artworkRepository;
        this.artworkService = artworkService;
        this.workshopService = workshopService;
    }

    @RequestMapping({"/", "/homepage"})
    public String homepage(Model model){
        List<Artwork> artworks = artworkRepository.getArtworks();
        List<Artwork> featured = artworks.subList(artworks.size()-6, artworks.size());


        Map<Object, String> artAndOwner = new HashMap<Object, String>();

        for(Artwork artwork: featured){
            artAndOwner.put(artwork, artworkService.getArtOwnerName(artwork));
        }

        System.out.println("In home controller : " + featured);

        model.addAttribute("artworks", featured);
        model.addAttribute("artAndOwner", artAndOwner);
        System.out.println(model);

        List<Workshop> workshops = workshopService.getWorkshops();
        Map<Object, String> workshopAndOrganizer = new HashMap<>();
        for(Workshop workshop: workshops){
            workshopAndOrganizer.put(workshop, workshopService.getWorkshopOrganizerName(workshop));
        }
        model.addAttribute("workshops", workshops);
        model.addAttribute("workshopAndOrganizer", workshopAndOrganizer);


        return "homepage";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
