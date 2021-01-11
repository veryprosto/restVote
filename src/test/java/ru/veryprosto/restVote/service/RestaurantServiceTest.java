package ru.veryprosto.restVote.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.veryprosto.restVote.config.AppConfig;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.veryprosto.restVote.RestaurantTestData.*;


@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/initDB_hsql.sql", config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;


    @Test
    public void delete() {
        restaurantService.delete(RESTAURANT_BY_OWNER1, OWNER1);
        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT_BY_OWNER1));
    }

    @Test
    public void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> restaurantService.delete(NOT_FOUND, OWNER1));
    }

    @Test
    public void getByUserId() {
        Restaurant restaurant = restaurantService.getByUserId(RESTAURANT_BY_OWNER1, OWNER1);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurantService.get(RESTAURANT_BY_OWNER1));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.get(NOT_FOUND));
    }

    @Test
    public void createWithException1() {
        assertThrows(ConstraintViolationException.class, () -> restaurantService.create(new Restaurant("ffff", 1000000000), OWNER1));
    }

    @Test
    public void createWithException2() {
        assertThrows(ConstraintViolationException.class, () -> restaurantService.create(new Restaurant(null, 1000), OWNER1));
    }

    @Test
    public void getAll() {
        List<Restaurant> all = restaurantService.getAll();
        assertEquals(all.size(), 6);
    }

    @Test
    public void getAllByUser() {
        List<Restaurant> allByUser = restaurantService.getAllByUser(OWNER2);
        assertEquals(allByUser.size(), 3);
    }

    @Test
    public void update() {
    }

    @Test
    public void create() {
    }
}