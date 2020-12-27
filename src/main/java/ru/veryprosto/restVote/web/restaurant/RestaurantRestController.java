package ru.veryprosto.restVote.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.service.RestService;
import ru.veryprosto.restVote.web.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.veryprosto.restVote.util.ValidationUtil.assureIdConsistent;
import static ru.veryprosto.restVote.util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final RestService service;

    public RestaurantRestController(RestService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable(value = "id") int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {} for user {}", id, userId);
        return service.get(id, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {} for user {}", id, userId);
        service.delete(id, userId);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    @PutMapping("/create")
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        int userId = SecurityUtil.authUserId();
        Restaurant restaurant = new Restaurant("", 0);
        checkNew(restaurant);
        log.info("create {} for user {}", restaurant, userId);
        //request.setAttribute("restaurant", restaurant);
        //request.getRequestDispatcher("/restForm.jsp").forward(request, response);
        service.create(restaurant, userId);
        model.addAttribute("restaurant", restaurant);
        modelAndView.setViewName("restForm");
        return modelAndView;
    }

    @PostMapping("/{id}/update")
    public void update(@PathVariable(value = "id") int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = SecurityUtil.authUserId();
        Restaurant restaurant = get(id);
        assureIdConsistent(restaurant, id);
        log.info("update {} for user {}", restaurant, userId);
        service.update(restaurant, userId);
        request.setAttribute("restaurant", restaurant);
        request.getRequestDispatcher("/restForm.jsp").forward(request, response);
    }


}