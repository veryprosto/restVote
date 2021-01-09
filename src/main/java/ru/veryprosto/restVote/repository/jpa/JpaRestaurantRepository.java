package ru.veryprosto.restVote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.model.User;
import ru.veryprosto.restVote.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant, int userId) {
        restaurant.setUser(em.getReference(User.class, userId));
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else if (getByUserId(restaurant.id(), userId) == null) {
            return null;
        }
        return em.merge(restaurant);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Restaurant getByUserId(int id, int userId) {
        Restaurant restaurant = em.find(Restaurant.class, id);
        return restaurant != null && restaurant.getUser().getId() == userId ? restaurant : null;
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getAllByUserId(int userId) {
        return em.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL, Restaurant.class)
                .getResultList();
    }
}