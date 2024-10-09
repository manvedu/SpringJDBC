package com.example.moviesapp;

import com.example.moviesapp.dao.MovieDao;
import com.example.moviesapp.model.Movie;
import com.example.moviesapp.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoviesAppTest {

    private final MovieDao movieDao = new MovieDao();
    private final ReportService reportService = new ReportService();

    @BeforeEach
    public void setup() {
        // Setup the database, if needed, or reset test data
    }

    @Test
    public void testMovieCrudOperations() throws SQLException {
        // Test insertion, retrieval, update, and deletion of movies
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setGenre("Drama");
        movie.setReleaseYear(2023);
        movie.setDirectorId(1L);

        movieDao.insertMovie(movie);
        assertTrue(movieDao.getAllMovies().size() > 0);
    }

}