package ru.veryprosto.restVote.service;

import org.springframework.stereotype.Service;
import ru.veryprosto.restVote.model.Role;
import ru.veryprosto.restVote.model.User;

@Service
public class SecurityManager {

    private User user = null;

    public User currentUser() {
        return user;
    }

    public int authUserId() {
        return user.getId();
    }

    public void setAuthUserId(User user) {
        this.user = user;
    }

    public void logout() {
        user = null;
    }

    public Role getCurrentUserRole() {
        return currentUser().getRoles().stream().findFirst().get();
    }
}
