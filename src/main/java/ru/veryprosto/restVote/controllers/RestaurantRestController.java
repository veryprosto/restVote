package ru.veryprosto.restVote.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.service.RestaurantService;
import ru.veryprosto.restVote.service.SecurityManager;

import static ru.veryprosto.restVote.util.Util.safetyConvertToUTF8;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController {
    static final String REST_URL = "/restaurants";

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final RestaurantService service;
    private final SecurityManager securityManager;

    public RestaurantRestController(RestaurantService service, SecurityManager securityManager) {
        this.service = service;
        this.securityManager = securityManager;
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable(value = "id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        int userId = securityManager.authUserId();
        log.info("get restaurant {} for user {}", id, userId);
        model.addAttribute("restaurant", service.get(id, userId));
        model.addAttribute("action_create", false);
        modelAndView.setViewName("restaurantForm");
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable(value = "id") String id, Model model) {
        int userId = securityManager.authUserId();
        log.info("delete restaurant {} for user {}", id, userId);
        service.delete(Integer.parseInt(id), userId);
        return getAll(model);
    }

    @GetMapping("/create")
    public ModelAndView create(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Restaurant restaurant = new Restaurant("", 0);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("action_create", true);
        modelAndView.setViewName("/restaurantForm");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView update(Model model, Restaurant restaurant) {
        restaurant.setName(safetyConvertToUTF8(restaurant.getName()));
        int userId = securityManager.authUserId();
        log.info("update {} for user {}", restaurant, userId);
        service.update(restaurant, userId);
        model.addAttribute("restaurant", restaurant);
        return getAll(model);
    }

    @GetMapping
    public ModelAndView getAll(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        int userId = securityManager.authUserId();
        modelAndView.setViewName("restaurants");
        model.addAttribute("restaurantList", service.getAll(userId));
        return modelAndView;
    }

    @PostMapping("/{id}/like")
    public ModelAndView like(Model model, @PathVariable(value = "id") int id) {
        return vote(model, id, 1);
    }

    @PostMapping("/{id}/unlike")
    public ModelAndView unlike(Model model, @PathVariable(value = "id") int id) {
        return vote(model, id, -1);
    }

    private ModelAndView vote(Model model, int id, int value) {
        ModelAndView modelAndView = new ModelAndView();
        int userId = securityManager.authUserId();

        Restaurant restaurant = service.get(id, userId);

        int rating = restaurant.getRating() + value;
        restaurant.setRating(rating);

        service.update(restaurant, userId);

        modelAndView.setViewName("restaurants");
        model.addAttribute("restaurantList", service.getAll(userId));
        return modelAndView;
    }
}