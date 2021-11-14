package com.ken.infinity.controllers;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.Orders;
import com.ken.infinity.models.User;
import com.ken.infinity.services.ArtworkService;
import com.ken.infinity.services.OrdersService;
import com.ken.infinity.services.SecurityService;
import com.ken.infinity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OrderController {

    UserService userService;
    SecurityService securityService;
    OrdersService ordersService;
    ArtworkService artworkService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public OrderController(UserService userService, SecurityService securityService, OrdersService ordersService, ArtworkService artworkService) {
        this.userService = userService;
        this.securityService = securityService;
        this.ordersService = ordersService;
        this.artworkService = artworkService;
    }

    @PostMapping("/order")
    public String addOrder(@ModelAttribute("orders") Orders orders, @RequestParam("artwork_id") int artwork_id, Model model){
        model.addAttribute("loggedIn", securityService.isLoggedIn());
        int currentUserId;
        currentUserId = userService.findByUsername(securityService.findLoggedInUsername()).getId();
        User user = userService.findByUserId(currentUserId);
        System.out.println(user);
        System.out.println(artwork_id);
        Artwork artwork = artworkService.findArtworkById(artwork_id);
        int price = artwork.getPrice();
        orders.setPrice(price);
        System.out.println(price);
        Date date = new Date();
        Timestamp ts = new Timestamp(date. getTime());
        ts.setTime(1000*(long)Math.floor(ts.getTime()/ 1000));
        orders.setOrdered_at(ts);
        System.out.println(ts);
        artworkService.updateArtwork(artwork_id);
        ordersService.save(orders, user, artwork );

//      start sending mail

        String from = "nairobi.sen.42@gmail.com";
        String to = user.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Your order for Artwork from Infinity Art Gallery");
        message.setText("Hello " + user.getFirstName() + "! \n" +
                "Thanks for your order #" + orders.getId() + " placed on " + orders.getOrdered_at() + " with Infinity Art Gallery." +
                " One of the best artwork from our gallery is headed your way! \n" +
                "\n" +
                "Your Order total is " + orders.getPrice() + "$. " +
                "We accept payment via cheque/debit/credit card. Simply reply to this mail to let us know how you wish to pay. We will send you a mail for further proceedings. " +
                "If you wish to cancel the order, let us know via replying to this mail. The due date for the payment is upto 15 days after recieving this mail. After that we may have to cancel your order. \n" +
                "\n" +
                "We love your choice of this master piece! If you have any queries, just reply to this mail and we'll be right back to you!" +
                "\n" +
                "\n" +
                "Sincerely, \n" +
                "Infinity Art Gallery");

        javaMailSender.send(message);


//        end sending mail


        return "redirect:/homepage";

    }



}
