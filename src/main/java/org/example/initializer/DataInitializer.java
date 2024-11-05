package org.example.initializer;

import org.example.dao.UserDao;
import org.example.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final UserDao userDao;

    public DataInitializer(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostConstruct
    public void init() {
        // Add some sample users to the in-memory UserDao
        userDao.save(new User(1L, "Mari Mar", "Mari_Mar@example.com"));
        userDao.save(new User(2L, "Betty Pinzon", "Betty_Pinzon@example.com"));
        userDao.save(new User(3L, "Jhon Doe", "Jhon_Doe@example.com"));
    }
}
