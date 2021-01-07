package ru.veryprosto.restVote.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.veryprosto.restVote.model.User;
import ru.veryprosto.restVote.model.Role;
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

    @RequestMapping(value = "/login")
    public String loginPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(value = "/registration")
    public String regPage(Authentication authentication, @ModelAttribute("role") String inputRole) {
        Role role = Role.valueOf(inputRole);
User user = new User()
            Role.USER ->
            Role.OWNER ->
            Role.ADMIN ->



        User user

        if (authentication != null) {
            return "redirect:/";
        }
        return "login";
    }

    /*@GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:restaurants";
    }

    @GetMapping("/restaurants")
    public String getRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAll(SecurityUtil.authUserId()));
        return "restaurants";
    }*/
}