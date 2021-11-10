package com.ken.infinity.controllers;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.repository.ArtworkRepository;
import com.ken.infinity.services.ArtworkService;
import com.ken.infinity.services.SecurityService;
import com.ken.infinity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShopController {

    UserService userService;
    ArtworkRepository artworkRepository;
    ArtworkService artworkService;
    SecurityService securityService;

    @Autowired
    public ShopController(UserService userService, ArtworkRepository artworkRepository, ArtworkService artworkService, SecurityService securityService) {
        this.userService = userService;
        this.artworkRepository = artworkRepository;
        this.artworkService = artworkService;
        this.securityService = securityService;
    }

    @GetMapping({"/shop"})
    public String shop(Model model){
        List<Artwork> artworks = artworkRepository.getArtworks();

        Map<Object, String> artAndOwner = new HashMap<Object, String>();
        for(Artwork artwork: artworks){
            artAndOwner.put(artwork, artworkService.getArtOwnerName(artwork));
        }

        System.out.println("In controller : " + artworks);

        model.addAttribute("artworks", artworks);
        model.addAttribute("artAndOwner", artAndOwner);
        System.out.println(model);

        return "shop";
    }

    @GetMapping("/hatching")
    public String hatching(Model model){
        List<Artwork> artworks = artworkRepository.getArtworks();

        Map<Object, String> artAndOwner = new HashMap<Object, String>();
        for(Artwork artwork: artworks){
            artAndOwner.put(artwork, artworkService.getArtOwnerName(artwork));
        }

        System.out.println("In controller : " + artworks);

        model.addAttribute("artworks", artworks);
        model.addAttribute("artAndOwner", artAndOwner);
        System.out.println(model);

        return "hatching";
    }

    @GetMapping("/watercolorPainting")
    public String watercolorPainting(Model model){
        List<Artwork> artworks = artworkRepository.getArtworks();

        Map<Object, String> artAndOwner = new HashMap<Object, String>();
        for(Artwork artwork: artworks){
            artAndOwner.put(artwork, artworkService.getArtOwnerName(artwork));
        }

        System.out.println("In controller : " + artworks);

        model.addAttribute("artworks", artworks);
        model.addAttribute("artAndOwner", artAndOwner);
        System.out.println(model);

        return "watercolorPainting";
    }

    @GetMapping("/oilPainting")
    public String oilPainting(Model model){
        List<Artwork> artworks = artworkRepository.getArtworks();

        Map<Object, String> artAndOwner = new HashMap<Object, String>();
        for(Artwork artwork: artworks){
            artAndOwner.put(artwork, artworkService.getArtOwnerName(artwork));
        }

        System.out.println("In controller : " + artworks);

        model.addAttribute("artworks", artworks);
        model.addAttribute("artAndOwner", artAndOwner);
        System.out.println(model);

        return "oilPainting";
    }

    @GetMapping("/shop/{artId}")
    public String ArtworkById(Model model, @PathVariable("artId") int artworkId){
        model.addAttribute("loggedIn", securityService.isLoggedIn());
        Map<String, Object> map = new HashMap<String, Object>();
        Artwork artwork = artworkService.findArtworkById(artworkId);
        map.put("artwork",artwork);
        model.addAttribute("owner", artworkService.getArtOwnerName(artwork));
        model.addAllAttributes(map);
        if(securityService.isLoggedIn())
            return "singleArt";
        else return "login";
    }


}
