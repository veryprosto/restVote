package ru.veryprosto.restVote.model;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class BaseEntityListener {

    @PrePersist
    public void preCreate(AbstractEntity entity) {
        Date date = new Date();
        entity.setModify(date);
    }


    @PreUpdate
    public void preUpdate(AbstractEntity entity) {
        Date date = new Date();
        entity.setModify(date);
    }
}
