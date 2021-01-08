package ru.veryprosto.restVote.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.veryprosto.restVote.model.Dish;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.service.DishService;
import ru.veryprosto.restVote.service.RestaurantService;
import ru.veryprosto.restVote.web.SecurityUtil;

import static ru.veryprosto.restVote.util.Util.safetyConvertToUTF8;

@RestController
@RequestMapping(MenuRestController.REST_URL)
public class MenuRestController {
    static final String REST_URL = "/menu";

    private static final Logger log = LoggerFactory.getLogger(MenuRestController.class);
    private final DishService service;

    public MenuRestController(DishService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable(value = "id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        int restaurantId = SecurityUtil.authRestaurantId();
        log.info("get dish {} from restaurant {}", id, restaurantId);
        model.addAttribute("dish", service.get(id, restaurantId));
        model.addAttribute("action_create", false);
        modelAndView.setViewName("dishForm");
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable(value = "id") String id, Model model) {
        int restaurantId = SecurityUtil.authRestaurantId();
        log.info("delete dish {} from restaurant {}", id, restaurantId);
        service.delete(Integer.parseInt(id), restaurantId);
        return getAll(model);
    }

    @GetMapping("/create")
    public ModelAndView create(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Dish dish = new Dish("", 0);
        model.addAttribute("dish", dish);
        model.addAttribute("action_create", true);
        modelAndView.setViewName("/dishForm");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView update(Model model, Dish dish) {
        dish.setName(safetyConvertToUTF8(dish.getName()));
        int restaurantId = SecurityUtil.authRestaurantId();
        log.info("update {} from restaurant {}", dish, restaurantId);
        service.update(dish, restaurantId);
        model.addAttribute("dish", dish);
        return getAll(model);
    }

    @GetMapping
    public ModelAndView getAll(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        int restaurantId = SecurityUtil.authRestaurantId();
        modelAndView.setViewName("menu");
        model.addAttribute("menuList", service.getAll(restaurantId));
        return modelAndView;
    }
}