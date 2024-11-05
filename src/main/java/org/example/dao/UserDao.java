package org.example.dao;

import org.example.model.User;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;

@Repository
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

    public List<User> findByName(String name) {
        List<User> users = new ArrayList<>();
        for (User user : userStorage.values()) {
            if (user.getName().equalsIgnoreCase(name)) {
                users.add(user);
            }
        }
        return users;
    }
}
