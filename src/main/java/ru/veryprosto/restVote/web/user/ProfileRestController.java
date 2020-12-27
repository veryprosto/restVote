package ru.veryprosto.restVote.web.user;

import org.springframework.stereotype.Controller;
import ru.veryprosto.restVote.model.User;

import static ru.veryprosto.restVote.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}