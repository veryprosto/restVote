package ru.veryprosto.restVote.web;

import ru.veryprosto.restVote.model.AbstractEntity;

public class SecurityUtil {

    private static int id = AbstractEntity.START_SEQ;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authRestaurantId() {
        return 100005;
    }
}