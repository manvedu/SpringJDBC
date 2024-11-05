package org.example.dao;

import org.example.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private Map<Long, User> userStorage = new HashMap<>();

    public User save(User user) {
        userStorage.put(user.getId(), user);
        return user;
    }

    public User getById(Long id) {
        return userStorage.get(id);
    }

    public Map<Long, User> getAll() {
        return userStorage;
    }
}
