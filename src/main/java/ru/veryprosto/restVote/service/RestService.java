package ru.veryprosto.restVote.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.repository.RestRepository;

import java.util.List;

import static ru.veryprosto.restVote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestService {

    private final RestRepository repository;

    public RestService(RestRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Restaurant> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "meal must not be null");
        checkNotFoundWithId(repository.save(restaurant, userId), restaurant.id());
    }

    public Restaurant create(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "meal must not be null");
        return repository.save(restaurant, userId);
    }
}