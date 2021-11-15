package com.ken.infinity.controllers;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.Orders;
import com.ken.infinity.repository.ArtworkRepository;
import com.ken.infinity.services.ArtworkService;
import com.ken.infinity.services.OrdersService;
import com.ken.infinity.services.SecurityService;
import com.ken.infinity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShopController {

    UserService userService;
    ArtworkRepository artworkRepository;
    ArtworkService artworkService;
    SecurityService securityService;
    OrdersService ordersService;

    @Autowired
    public ShopController(UserService userService, ArtworkRepository artworkRepository, ArtworkService artworkService, SecurityService securityService, OrdersService ordersService) {
        this.userService = userService;
        this.artworkRepository = artworkRepository;
        this.artworkService = artworkService;
        this.securityService = securityService;
        this.ordersService = ordersService;
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


    @GetMapping("/receivedArtworks")
    public String receivedArtworks(Model model){
        List<Artwork> artworks = artworkRepository.getArtworks();

        Map<Object, String> artAndOwner = new HashMap<Object, String>();
        for(Artwork artwork: artworks){
            artAndOwner.put(artwork, artworkService.getArtOwnerName(artwork));
        }

        System.out.println("In controller : " + artworks);

        model.addAttribute("artworks", artworks);
        model.addAttribute("artAndOwner", artAndOwner);
        System.out.println(model);

        return "receivedArtworks";
    }

    @RequestMapping(value = "/acceptArt", method = { RequestMethod.GET, RequestMethod.POST })
    public String acceptArt(Model model,  @RequestParam("artwork_id") int artwork_id){

        artworkRepository.acceptArt(artwork_id);
        return "redirect:/receivedArtworks";
    }

    @RequestMapping(value = "/declineArt", method = {RequestMethod.GET, RequestMethod.POST})
    public String declineArt(Model model, @RequestParam("artwork_id") int artwork_id){
        artworkRepository.declineArt(artwork_id);
        return "redirect:/receivedArtworks";

    }

    @RequestMapping(value = "/shop/{artId}", method = { RequestMethod.GET, RequestMethod.POST })
    public String ArtworkById(Model model, @PathVariable("artId") int artworkId){
        model.addAttribute("loggedIn", securityService.isLoggedIn());
        Map<String, Object> map = new HashMap<String, Object>();
        Orders orders = new Orders();
        model.addAttribute(orders);
        Artwork artwork = artworkService.findArtworkById(artworkId);
        map.put("artwork",artwork);
        model.addAttribute("owner", artworkService.getArtOwnerName(artwork));
        model.addAllAttributes(map);

        artworkService.updateArtworkLikes(artworkId, artwork.getLikes());

        if(securityService.isLoggedIn())
            return "singleArt";
        else return "login";
    }


}
