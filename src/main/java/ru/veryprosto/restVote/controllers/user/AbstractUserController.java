package ru.veryprosto.restVote.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.veryprosto.restVote.model.User;
import ru.veryprosto.restVote.service.UserService;
import ru.veryprosto.restVote.service.SecurityManager;

import java.util.List;

import static ru.veryprosto.restVote.util.ValidationUtil.assureIdConsistent;
import static ru.veryprosto.restVote.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @Autowired
    public SecurityManager securityManager;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
