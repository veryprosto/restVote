package ru.veryprosto.restVote.repository;

import ru.veryprosto.restVote.model.UserVote;

import java.util.List;

public interface UserVoteRepository {

    UserVote save(UserVote userVote);

    UserVote get(int id);

    UserVote getByUserWithRestaurantId(int user_id, int restaurantId);

    List<UserVote> getVotesByRestaurant(int restaurantId);
}
