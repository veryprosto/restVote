package ru.veryprosto.restVote.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.veryprosto.restVote.model.User;
import ru.veryprosto.restVote.service.UserService;
import ru.veryprosto.restVote.web.SecurityUtil;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class MainRestController {

    private UserService userService;

    public MainRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public RedirectView  index(Model model, Principal principal, RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "/");

        if (principal != null) {
            User user = userService.getByName(principal.getName());
            model.addAttribute("message", "You are logged in as " + user.getName() + " " + user.getRoles().stream().findFirst());
            SecurityUtil.setAuthUserId(user.getId());
        }

        return new RedirectView("/restaurants");
    }

}