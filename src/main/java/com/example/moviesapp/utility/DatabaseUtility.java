package com.example.moviesapp.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {
    //Create database before
    private static final String URL = "jdbc:postgresql://localhost:5432/moviesdb";
    private static final String USER = "mariavelandia";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
