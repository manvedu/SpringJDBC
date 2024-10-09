package com.example.social_network.service;

import com.example.social_network.dao.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void printUsersWithHighFriendAndLikeCount() {
        List<String> userNames = userDao.findUsersWithHighFriendAndLikeCount();
        System.out.println("Users with more than 100 friends and likes in March 2025:");
        userNames.forEach(System.out::println);
    }
}
