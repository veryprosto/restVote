package ru.veryprosto.restVote.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.web.restaurant.RestaurantRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RestServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private RestaurantRestController restController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        restController = springContext.getBean(RestaurantRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Restaurant restaurant = new Restaurant(
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("rating")));

        if (StringUtils.isEmpty(request.getParameter("id"))) {
   //         restController.create(restaurant);
        } else {
     //       restController.update(restaurant, getId(request));
        }
        response.sendRedirect("restaurants");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getId(request);
                restController.delete(id);
                response.sendRedirect("restaurants");
            }
            case "create", "update" -> {
                final Restaurant restaurant = "create".equals(action) ?
                        new Restaurant("", 0) :
                        restController.get(getId(request));

                request.setAttribute("restaurant", restaurant);
                request.getRequestDispatcher("/restForm.jsp").forward(request, response);
            }
            default -> {
                request.setAttribute("restaurants", restController.getAll());
                request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
            }
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}