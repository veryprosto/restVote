package ru.veryprosto.restVote.repository;

import ru.veryprosto.restVote.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);
}
