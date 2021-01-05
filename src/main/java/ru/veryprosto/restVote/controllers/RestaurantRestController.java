package ru.veryprosto.restVote.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.service.RestService;
import ru.veryprosto.restVote.web.SecurityUtil;

import java.util.List;

import static ru.veryprosto.restVote.util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final RestService service;
    private final MainRestController mainRestController;

    public RestaurantRestController(RestService service, MainRestController mainRestController) {
        this.service = service;
        this.mainRestController = mainRestController;
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable(value = "id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {} for user {}", id, userId);
        model.addAttribute("restaurant", service.get(id, userId));
        modelAndView.setViewName("restForm");
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public void delete(@PathVariable(value = "id") String id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {} for user {}", id, userId);
        service.delete(Integer.parseInt(id) , userId);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    @PostMapping("/create")
    public ModelAndView create(Model model) {
        int userId = SecurityUtil.authUserId();
        Restaurant restaurant = new Restaurant("", 0);
        checkNew(restaurant);
        log.info("create {} for user {}", restaurant, userId);
        service.create(restaurant, userId);
        model.addAttribute("restaurant", restaurant);
        return mainRestController.defineRestaurantsMethod(userId, model);
    }

    @PostMapping
    public ModelAndView update(Model model, Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} for user {}", restaurant, userId);
        service.update(restaurant, userId);
        model.addAttribute("restaurant", restaurant);
        return mainRestController.defineRestaurantsMethod(userId, model);
    }
}