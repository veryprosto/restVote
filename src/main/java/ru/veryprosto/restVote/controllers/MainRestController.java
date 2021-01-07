package ru.veryprosto.restVote.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veryprosto.restVote.service.RestaurantService;
import ru.veryprosto.restVote.service.UserService;

@RestController
@RequestMapping("/")
public class MainRestController {
    private final RestaurantService restaurantService;
    private final UserService userService;


    public MainRestController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @RequestMapping
    public String mainPage(Model model) {
        return "index";
    }

}