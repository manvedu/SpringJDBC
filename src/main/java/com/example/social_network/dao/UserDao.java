package com.example.social_network.dao;

import com.example.social_network.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addUser(User user) {
        String sql = "INSERT INTO Users (name, surname, birthdate) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getBirthdate());
    }

}
