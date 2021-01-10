package ru.veryprosto.restVote.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.veryprosto.restVote.model.UserVote;
import ru.veryprosto.restVote.repository.UserVoteRepository;

import java.util.List;

import static ru.veryprosto.restVote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserVoteService {

    private final UserVoteRepository repository;

    public UserVoteService(UserVoteRepository repository) {
        this.repository = repository;
    }

    public UserVote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void update(UserVote userVote) {
        Assert.notNull(userVote, "UserVote must not be null");
        checkNotFoundWithId(repository.save(userVote), userVote.id());
    }

    public UserVote create(UserVote userVote) {
        Assert.notNull(userVote, "UserVote must not be null");
        return repository.save(userVote);
    }

    public UserVote getByUserWithRestaurantId(int userId, int restaurantId) {
        return repository.getByUserWithRestaurantId(userId, restaurantId);
    }

    public int collectVotes(int restaurantId) {
        List<UserVote>  list = repository.getVotesByRestaurant(restaurantId);
        return list.stream().map(UserVote::getVote).mapToInt(Integer::intValue).sum();
    }

}