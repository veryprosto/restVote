package ru.veryprosto.restVote.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.model.User;
import ru.veryprosto.restVote.model.UserVote;
import ru.veryprosto.restVote.service.RestaurantService;
import ru.veryprosto.restVote.service.SecurityManager;
import ru.veryprosto.restVote.service.UserVoteService;

import java.time.LocalTime;
import java.util.List;

import static ru.veryprosto.restVote.util.Util.safetyConvertToUTF8;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController {
    static final String REST_URL = "/restaurants";

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final RestaurantService service;
    private final SecurityManager securityManager;
    private final UserVoteService userVoteService;

    public RestaurantRestController(RestaurantService service, SecurityManager securityManager, UserVoteService userVoteService) {
        this.service = service;
        this.securityManager = securityManager;
        this.userVoteService = userVoteService;
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable(value = "id") int id, Model model) {
        //TODO: проверить роль!!!
        ModelAndView modelAndView = new ModelAndView();
        int userId = securityManager.authUserId();
        log.info("get restaurant {} for user {}", id, userId);
        model.addAttribute("restaurant", service.getByUserId(id, userId));
        model.addAttribute("action_create", false);
        modelAndView.setViewName("restaurantForm");
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable(value = "id") String id, Model model) {
        //TODO: проверить роль!!!
        int userId = securityManager.authUserId();
        log.info("delete restaurant {} for user {}", id, userId);
        service.delete(Integer.parseInt(id), userId);
        return getAll(model);
    }

    @GetMapping("/create")
    public ModelAndView create(Model model) {
        //TODO: проверить роль!!!
        ModelAndView modelAndView = new ModelAndView();
        Restaurant restaurant = new Restaurant("", 0);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("action_create", true);
        modelAndView.setViewName("/restaurantForm");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView update(Model model, Restaurant restaurant) {
        //TODO: проверить роль!!!
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
        model.addAttribute("role", securityManager.getCurrentUserRole().name());
        model.addAttribute("restaurantList", getRestaurantsListByRole());
        modelAndView.setViewName("restaurants");
        return modelAndView;
    }

    @PostMapping("/{id}/like")
    public ModelAndView like(Model model, @PathVariable(value = "id") int id) {
        //TODO: проверить роль!!!
        return vote(model, id, 1);
    }

    @PostMapping("/{id}/unlike")
    public ModelAndView unlike(Model model, @PathVariable(value = "id") int id) {
        //TODO: проверить роль!!!
        return vote(model, id, -1);
    }

    private ModelAndView vote(Model model, int id, int value) {

        Restaurant restaurant = service.get(id);
        User user = securityManager.currentUser();
        UserVote currentUserVote = userVoteService.getByUserWithRestaurantId(user.getId(),restaurant.id());
        LocalTime lc = LocalTime.now();

        if (currentUserVote == null || lc.isBefore(LocalTime.of(11,0))) {

            //вытаскиваем все голоса по текущему ресторану
            int newRating = userVoteService.collectVotes(restaurant.id());
            int savedVote = currentUserVote != null ? currentUserVote.getVote() : 0;
            int correctChangeRating = savedVote*(-1) + value;

            //TODO: изменить границы, чтобы можно было уходть в минус

            if (currentUserVote == null) {
                UserVote vote = new UserVote(restaurant, user, value);
                userVoteService.create(vote);
            } else {
                currentUserVote.setVote(value);
                userVoteService.update(currentUserVote);
            }

            int rating = restaurant.getRating() + correctChangeRating;
            restaurant.setRating(rating);

            //обновляем ресторан с указанием владельца
            service.update(restaurant, restaurant.getUser().getId());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("restaurants");
        model.addAttribute("restaurantList", service.getAll());
        return modelAndView;
    }

    private List<Restaurant> getRestaurantsListByRole() {
        List<Restaurant> restaurantList = null;
        switch (securityManager.getCurrentUserRole()) {
            case OWNER:
                restaurantList = service.getAllByUser(securityManager.authUserId());
                break;
            case USER:
                restaurantList = service.getAll();
                break;
        }
        return  restaurantList;
    }
}