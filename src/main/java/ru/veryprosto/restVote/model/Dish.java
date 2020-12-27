package ru.veryprosto.restVote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"rest_id"}, name = "dish_unique_restaurant_name_dish_name_idx")})
public class Dish extends AbstractEntity {
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Dish() {

    }

    public Dish(String name, int price) {
        this(null, name, price);
    }

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}