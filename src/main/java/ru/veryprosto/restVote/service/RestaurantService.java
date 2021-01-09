package ru.veryprosto.restVote.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.repository.RestaurantRepository;

import java.util.List;

import static ru.veryprosto.restVote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant getByUserId(int id, int userId) {
        return checkNotFoundWithId(repository.getByUserId(id, userId), id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Restaurant> getAllByUser(int userId) {
        return repository.getAllByUserId(userId);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public void update(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant, userId), restaurant.id());
    }

    public Restaurant create(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant, userId);
    }
}