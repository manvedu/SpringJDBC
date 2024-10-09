package com.example.moviesapp.dao;

import com.example.moviesapp.model.Movie;
import com.example.moviesapp.utility.DatabaseUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    public void insertMovie(Movie movie) throws SQLException {
        String sql = "INSERT INTO Movies (title, genre, release_year, director_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.setLong(4, movie.getDirectorId());
            stmt.executeUpdate();
        }
    }

    public List<Movie> getAllMovies() throws SQLException {
        String sql = "SELECT * FROM Movies";
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getLong("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setReleaseYear(rs.getInt("release_year"));
                movie.setDirectorId(rs.getLong("director_id"));
                movies.add(movie);
            }
        }
        return movies;
    }

    public void updateMovie(Movie movie) throws SQLException {
        String sql = "UPDATE Movies SET title = ?, genre = ?, release_year = ?, director_id = ? WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.setLong(4, movie.getDirectorId());
            stmt.setLong(5, movie.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteMovie(Long id) throws SQLException {
        String sql = "DELETE FROM Movies WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}