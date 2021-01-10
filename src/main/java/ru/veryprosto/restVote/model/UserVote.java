package ru.veryprosto.restVote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = UserVote.ALL_BY_RESTAURANT, query = "SELECT v FROM UserVote v WHERE v.restaurant.id =:restaurantId"),
        @NamedQuery(name = UserVote.FIND_BY_USER_WITH_RESTAURANT, query = "SELECT v FROM UserVote v WHERE v.user.id=:userId AND v.restaurant.id=:restaurantId")
})

@Entity
@Table(name = "user_votes")
public class UserVote extends AbstractEntity{
    public static final String ALL_BY_RESTAURANT = "UserVote.allByRestaurant";
    public static final String FIND_BY_USER_WITH_RESTAURANT = "UserVote.findByUserAndRestaurant";

    public UserVote() {
    }

    public UserVote(Integer id, String name) {
        super(id, name);
    }

    public UserVote(Restaurant restaurant, User user, int vote) {
        setRestaurant(restaurant);
        setUser(user);
        setVote(vote);
        setName("some_name");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "vote")
    private Integer vote;

}