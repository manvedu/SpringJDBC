package com.example.highloadtool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HighloadService {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final int numTables;
    private final int numColumns;
    private final String[] columnTypes;
    private final int[] rowCounts;
    private final int numThreads;

    public HighloadService(Properties config) {
        this.dbUrl = config.getProperty("db.url");
        this.dbUser = config.getProperty("db.user");
        this.dbPassword = config.getProperty("db.password");
        this.numTables = Integer.parseInt(config.getProperty("num.tables"));
        this.numColumns = Integer.parseInt(config.getProperty("num.columns"));
        this.columnTypes = config.getProperty("column.types").split(",");
        this.rowCounts = parseRowCounts(config.getProperty("row.counts"));
        this.numThreads = Integer.parseInt(config.getProperty("num.threads"));
    }

    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numTables; i++) {
            int rows = rowCounts[i];
            executor.submit(() -> {
                try {
                    createAndPopulateTable(rows);
                } catch (SQLException e) {
                    System.err.println("Error creating/populating table: " + e.getMessage());
                }
            });
        }
        executor.shutdown();
    }


    private void createAndPopulateTable(int rows) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String tableName = generateRandomString(8);  // Generate an 8-character random table name
            String createTableSQL = generateCreateTableSQL(tableName);
            conn.createStatement().execute(createTableSQL);

            for (int i = 0; i < rows; i++) {
                String insertSQL = generateInsertSQL(tableName);
                conn.createStatement().execute(insertSQL);
            }
            System.out.println("Table created and populated: " + tableName);
        }
    }

    private String generateCreateTableSQL(String tableName) {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (id SERIAL PRIMARY KEY, ");
        for (int i = 1; i <= numColumns; i++) {
            sql.append("col").append(i).append(" ").append(columnTypes[i % columnTypes.length].trim()).append(",");
        }
        sql.setLength(sql.length() - 1); // Remove trailing comma
        sql.append(");");
        return sql.toString();
    }

    private String generateInsertSQL(String tableName) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " VALUES (DEFAULT, ");
        Random random = new Random();
        for (int i = 0; i < numColumns; i++) {
            if (columnTypes[i % columnTypes.length].trim().equalsIgnoreCase("VARCHAR")) {
                sql.append("'").append(generateRandomString(8)).append("',");
            } else {
                sql.append(random.nextInt(100)).append(",");
            }
        }
        sql.setLength(sql.length() - 1); // Remove trailing comma
        sql.append(");");
        return sql.toString();
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    private int[] parseRowCounts(String rowCountsStr) {
        String[] parts = rowCountsStr.split(",");
        int[] rowCounts = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            rowCounts[i] = Integer.parseInt(parts[i].trim());
        }
        return rowCounts;
    }
}