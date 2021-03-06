package ru.veryprosto.restVote.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.veryprosto.restVote.model.Dish;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.model.Role;
import ru.veryprosto.restVote.service.DishService;
import ru.veryprosto.restVote.service.RestaurantService;
import ru.veryprosto.restVote.service.SecurityManager;
import ru.veryprosto.restVote.util.exception.NotFoundException;

import static ru.veryprosto.restVote.util.Util.safetyConvertToUTF8;

@RestController
@RequestMapping(MenuRestController.REST_URL)
public class MenuRestController {
    static final String REST_URL = "restaurants/{restaurantId}/menu";

    private static final Logger log = LoggerFactory.getLogger(MenuRestController.class);
    private final DishService service;
    private final SecurityManager securityManager;
    private final RestaurantService restaurantService;

    public MenuRestController(DishService service, SecurityManager securityManager, RestaurantService restaurantService) {
        this.service = service;
        this.securityManager = securityManager;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("restaurantId") int restaurantId,
                            @PathVariable(value = "id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("get dish {} from restaurant {}", id, restaurantId);
        model.addAttribute("dish", service.get(id, restaurantId));
        model.addAttribute("action_create", false);
        model.addAttribute("restaurantId", restaurantId);
        modelAndView.setViewName("dishForm");
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("restaurantId") int restaurantId,
                               @PathVariable(value = "id") String id, Model model) {
        if (securityManager.getCurrentUserRole() != Role.OWNER) {
            throw new NotFoundException("роль не та!");
        }
        log.info("delete dish {} from restaurant {}", id, restaurantId);
        service.delete(Integer.parseInt(id), restaurantId);
        return getAll(restaurantId, model);
    }

    @GetMapping("/create")
    public ModelAndView create(@PathVariable("restaurantId") int restaurantId, Model model) {
        if (securityManager.getCurrentUserRole() != Role.OWNER) {
            throw new NotFoundException("роль не та!");
        }
        ModelAndView modelAndView = new ModelAndView();
        Dish dish = new Dish("", 0);
        model.addAttribute("dish", dish);
        model.addAttribute("action_create", true);
        model.addAttribute("restaurantId", restaurantId);
        modelAndView.setViewName("dishForm");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView update(@PathVariable("restaurantId") int restaurantId, Model model, Dish dish) {
        if (securityManager.getCurrentUserRole() != Role.OWNER) {
            throw new NotFoundException("роль не та!");
        }
        dish.setName(safetyConvertToUTF8(dish.getName()));
        log.info("update {} from restaurant {}", dish, restaurantId);
        service.update(dish, restaurantId);
        model.addAttribute("dish", dish);
        return getAll(restaurantId, model);
    }

    @GetMapping
    public ModelAndView getAll(@PathVariable("restaurantId") int restaurantId, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu");
        model.addAttribute("menuList", service.getAll(restaurantId));
        model.addAttribute("role", securityManager.getCurrentUserRole().name());
        model.addAttribute("restaurant", getRestaurantByRole(restaurantId));
        return modelAndView;
    }


    private Restaurant getRestaurantByRole(int restaurantId) {
        Restaurant restaurant = null;
        switch (securityManager.getCurrentUserRole()) {
            case OWNER:
                restaurant = restaurantService.getByUserId(restaurantId, securityManager.authUserId());
                break;
            case USER:
                restaurant = restaurantService.get(restaurantId);
                break;
        }
        return restaurant;
    }
}