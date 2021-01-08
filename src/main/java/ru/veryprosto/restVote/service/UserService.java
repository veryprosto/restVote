package ru.veryprosto.restVote.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.veryprosto.restVote.model.Role;
import ru.veryprosto.restVote.model.User;
import ru.veryprosto.restVote.repository.UserRepository;

import java.util.EnumSet;
import java.util.List;

import static ru.veryprosto.restVote.util.ValidationUtil.checkNotFound;
import static ru.veryprosto.restVote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public User create(String name, String email, String password, Role role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(EnumSet.of(role));
        return create(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public User getByName(String name) {
        Assert.notNull(name, "name must not be null");
        return checkNotFound(repository.getByName(name), "user name=" + name);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.id());
    }
}