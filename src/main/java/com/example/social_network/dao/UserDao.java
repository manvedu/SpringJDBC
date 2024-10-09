package com.example.social_network.dao;

import com.example.social_network.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Lazy
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addUser(User user) {
        String sql = "INSERT INTO Users (name, surname, birthdate) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getBirthdate());
    }

    public List<String> findUsersWithHighFriendAndLikeCount() {
        String sql = """
            SELECT DISTINCT u.name
            FROM "users" u
            JOIN "friendships" f ON u.id = f."userid1"
            JOIN "likes" l ON u.id = l."userid"
            WHERE f."timestamp" BETWEEN '2025-03-01' AND '2025-03-31'
            AND l."timestamp" BETWEEN '2025-03-01' AND '2025-03-31'
            GROUP BY u.name
            HAVING COUNT(DISTINCT f."userid2") > 100
            AND COUNT(DISTINCT l."postid") > 100
        """;

        return jdbcTemplate.queryForList(sql, String.class);
    }


}
