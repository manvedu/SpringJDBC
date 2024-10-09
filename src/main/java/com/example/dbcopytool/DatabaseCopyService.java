package com.example.dbcopytool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseCopyService {
    private final String sourceDbUrl;
    private final String targetDbUrl;
    private final String order;

    public DatabaseCopyService(String sourceDbUrl, String targetDbUrl, String order) {
        this.sourceDbUrl = sourceDbUrl;
        this.targetDbUrl = targetDbUrl;
        this.order = order;
    }

    public void copyDatabase() {
        try (Connection sourceConn = DriverManager.getConnection(sourceDbUrl);
             Connection targetConn = DriverManager.getConnection(targetDbUrl)) {

            List<String> tables = getTablesInLexicographicOrder(sourceConn);

            for (String table : tables) {
                copyTable(sourceConn, targetConn, table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> getTablesInLexicographicOrder(Connection conn) throws SQLException {
        List<String> tables = new ArrayList<>();
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
        }
        Collections.sort(tables); // Lexicographic order
        return tables;
    }

    private void copyTable(Connection sourceConn, Connection targetConn, String table) throws SQLException {
        System.out.println("Copying table: " + table);

        String query = "SELECT * FROM " + table + " ORDER BY id " + (order.equals("reverse") ? "DESC" : "ASC");
        try (Statement sourceStmt = sourceConn.createStatement();
             ResultSet rs = sourceStmt.executeQuery(query);
             PreparedStatement targetStmt = prepareInsertStatement(targetConn, table, rs)) {

            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    targetStmt.setObject(i, rs.getObject(i));
                }
                targetStmt.addBatch();
            }
            targetStmt.executeBatch();
            System.out.println("Table " + table + " copied successfully.");
        }
    }

    private PreparedStatement prepareInsertStatement(Connection targetConn, String table, ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(table).append(" (");

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            sql.append(metaData.getColumnName(i)).append(",");
        }
        sql.setLength(sql.length() - 1); // Remove trailing comma
        sql.append(") VALUES (");

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            sql.append("?,");
        }
        sql.setLength(sql.length() - 1); // Remove trailing comma
        sql.append(")");

        return targetConn.prepareStatement(sql.toString());
    }
}