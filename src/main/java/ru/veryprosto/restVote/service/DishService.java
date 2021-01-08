package ru.veryprosto.restVote.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.veryprosto.restVote.model.Dish;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.repository.DishRepository;
import ru.veryprosto.restVote.repository.RestaurantRepository;

import java.util.List;

import static ru.veryprosto.restVote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public void update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish, restaurantId), dish.id());
    }

    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId);
    }
}