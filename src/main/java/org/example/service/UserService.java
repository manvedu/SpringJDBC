package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(Long id, String name, String email) {
        User user = new User(id, name, email);
        logger.info("UserService - createUser: " + user.getName());
        return userDao.save(user);
    }

    public User getUser(Long id) {
        logger.info("UserService - getUser by id: " + id);
        return userDao.getById(id);
    }
}
