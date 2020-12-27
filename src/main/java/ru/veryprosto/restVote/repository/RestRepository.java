package ru.veryprosto.restVote.repository;

import ru.veryprosto.restVote.model.Restaurant;

import java.util.List;

public interface RestRepository {
    // null if updated restaurant do not belong to userId
    Restaurant save(Restaurant restaurant, int userId);

    // false if restaurant do not belong to userId
    boolean delete(int id, int userId);

    // null if restaurant do not belong to userId
    Restaurant get(int id, int userId);

    List<Restaurant> getAll(int userId);
}
