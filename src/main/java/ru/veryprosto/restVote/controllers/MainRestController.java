package ru.veryprosto.restVote.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.veryprosto.restVote.service.RestService;
import ru.veryprosto.restVote.web.SecurityUtil;

@RestController
@RequestMapping("/")
public class MainRestController {
    private static final Logger log = LoggerFactory.getLogger(MainRestController.class);

    private final RestService service;

    public MainRestController(RestService service) {
        this.service = service;
    }

    @PostMapping("/defineRestaurants")
    public ModelAndView defineRestaurantsMethod(@ModelAttribute("userId") int userId, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        //int userId = Integer.parseInt(userIdValue);
        SecurityUtil.setAuthUserId(userId);
        modelAndView.setViewName("restaurants");
        model.addAttribute("restaurantList", service.getAll(userId));
        return modelAndView;
    }

    @RequestMapping
    public String mainPage(Model model) {
        return "index";
    }

}