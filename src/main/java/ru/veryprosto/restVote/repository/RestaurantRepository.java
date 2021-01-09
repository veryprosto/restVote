package ru.veryprosto.restVote.repository;

import ru.veryprosto.restVote.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if updated restaurant do not belong to userId
    Restaurant save(Restaurant restaurant, int userId);

    // false if restaurant do not belong to userId
    boolean delete(int id, int userId);

    // null if restaurant do not belong to userId
    Restaurant getByUserId(int id, int userId);

    Restaurant get(int id);

    List<Restaurant> getAllByUserId(int userId);

    List<Restaurant> getAll();
}
