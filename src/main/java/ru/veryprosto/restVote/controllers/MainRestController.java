package ru.veryprosto.restVote.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.veryprosto.restVote.model.User;
import ru.veryprosto.restVote.service.SecurityManager;
import ru.veryprosto.restVote.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class MainRestController {

    private UserService userService;
    private SecurityManager securityManager;

    public MainRestController(UserService userService, SecurityManager securityManager) {
        this.userService = userService;
        this.securityManager = securityManager;
    }

    @GetMapping
    public RedirectView  index(Model model, Principal principal, RedirectAttributes attributes) {

        if (principal != null) {
            User user = securityManager.currentUser();
            model.addAttribute("message", "You are logged in as " + user.getName() + " " + user.getRoles().stream().findFirst());
        }
        return new RedirectView("/restaurants");
    }

    @GetMapping("closeSession")
    public RedirectView  closeSession() {
        securityManager.logout();
        return new RedirectView("/restaurants");
    }
}