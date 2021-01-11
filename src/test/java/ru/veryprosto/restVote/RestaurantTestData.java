package ru.veryprosto.restVote;

import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.service.UserService;

public class RestaurantTestData {
    public static final TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingEqualsComparator(Restaurant.class);

    public static final int NOT_FOUND = 1;
    public static final int USER = 100000;
    public static final int OWNER1 = 100001;
    public static final int OWNER2 = 100002;
    public static final int RESTAURANT_BY_OWNER1 = 100004;
    public static final int RESTAURANT_BY_OWNER2 = 100009;

    /*public static Restaurant getNew(){
        return new Restaurant("Астория", 1);
    }

    public static Restaurant getUpdated(){
        Restaurant updated = new Restaurant();
        updated.setName("Новое имя");
        updated.setRating(50);
    }*/






}
