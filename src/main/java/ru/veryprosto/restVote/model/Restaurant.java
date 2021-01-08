package ru.veryprosto.restVote.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r WHERE r.user.id=:userId ORDER BY r.rating DESC"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id AND r.user.id=:userId"),
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id"}, name = "restaurants_unique_user_idx")})
public class Restaurant extends AbstractEntity {
    public static final String ALL_SORTED = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";

    @Column(name = "rating", nullable = false)
    @Range(min = 0, max = 5000)
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Dish> menu = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(String name, int rating) {
        this(null, name, rating);
    }

    public Restaurant(Integer id, String name, int rating) {
        super(id, name);
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}