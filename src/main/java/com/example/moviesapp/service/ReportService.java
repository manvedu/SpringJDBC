package com.example.moviesapp.service;

import com.example.moviesapp.utility.DatabaseUtility;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReportService {

    public Map<String, Integer> getMoviesCountByGenre() throws SQLException {
        String sql = "SELECT genre, COUNT(*) AS count FROM Movies GROUP BY genre";
        Map<String, Integer> genreCount = new HashMap<>();

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                genreCount.put(rs.getString("genre"), rs.getInt("count"));
            }
        }
        return genreCount;
    }

    public Map<String, Double> getAverageRatingByMovie() throws SQLException {
        String sql = "SELECT title, AVG(rating) AS avg_rating FROM Movies m " +
                "JOIN Reviews r ON m.id = r.movie_id GROUP BY title";
        Map<String, Double> ratings = new HashMap<>();

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ratings.put(rs.getString("title"), rs.getDouble("avg_rating"));
            }
        }
        return ratings;
    }
}