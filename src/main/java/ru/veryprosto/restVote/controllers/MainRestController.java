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

//    private final RestService service;
//
//    public MainRestController(RestService service) {
//        this.service = service;
//    }

    /*  @GetMapping("/{id}")
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
  */
    /*@PutMapping("/create")
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        int userId = SecurityUtil.authUserId();
        Restaurant restaurant = new Restaurant("", 0);
        checkNew(restaurant);
        log.info("create {} for user {}", restaurant, userId);
        service.create(restaurant, userId);
        model.addAttribute("restaurant", restaurant);
        modelAndView.setViewName("restForm");
        return modelAndView;
    }*/

    /*
    @RequestMapping
    public String mainPage(Model model) {
    model.addAttribute("countCars", service.getCountCars());
    model.addAttribute("countRepairs", service.getCountRepairs());
    model.addAttribute("countRepairsComplete", service.getCountRepairsComplete());
    model.addAttribute("countOwners", service.getCountOwners());
    ModelHelper.addUserAuthModel(model,userAuthDto);
    return "main";
    }
     */

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