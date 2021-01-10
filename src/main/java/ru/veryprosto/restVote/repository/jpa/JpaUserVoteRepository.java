package ru.veryprosto.restVote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.model.UserVote;
import ru.veryprosto.restVote.repository.UserVoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserVoteRepository implements UserVoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserVote getByUserWithRestaurantId(int userId, int restaurantId) {
        return em.createNamedQuery(UserVote.FIND_BY_USER_WITH_RESTAURANT, UserVote.class)
                .setParameter("userId", userId)
                .setParameter("restaurantId", restaurantId)
                .getResultList().stream().filter( v->
                        v.getModify().after(
                                // 00:00 сегодня
                                Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
                        )
                ).findFirst().orElse(null);
    }

    @Override
    public List<UserVote> getVotesByRestaurant(int restaurantId) {
        return em.createNamedQuery(UserVote.ALL_BY_RESTAURANT, UserVote.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    @Override
    @Transactional
    public UserVote save(UserVote userVote) {
        userVote.setRestaurant(em.getReference(Restaurant.class, userVote.getRestaurant().getId()));
        if (userVote.isNew()) {
            em.persist(userVote);
            return userVote;
        } else if (get(userVote.id()) == null) {
            return null;
        }
        return em.merge(userVote);
    }

    @Override
    public UserVote get(int id) {
        return em.find(UserVote.class, id);
    }
}