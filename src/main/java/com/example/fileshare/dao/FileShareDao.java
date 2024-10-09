package com.example.fileshare.dao;

import com.example.fileshare.model.FileRecord;
import com.example.fileshare.utility.DatabaseUtility;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class FileShareDao {

    public Long saveFile(String name, byte[] data, Timestamp expiryDate) throws SQLException {
        String sql = "{ ? = call save_file(?, ?, ?) }";
        try (Connection conn = DatabaseUtility.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.BIGINT);
            stmt.setString(2, name);
            stmt.setBytes(3, data);
            stmt.setTimestamp(4, expiryDate);

            stmt.execute();
            return stmt.getLong(1);
        }
    }

    public FileRecord retrieveFile(Long id) throws SQLException, IOException {
        String sql = "{ call retrieve_file(?) }";
        try (Connection conn = DatabaseUtility.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                FileRecord file = new FileRecord();
                file.setId(id);
                file.setName(rs.getString("file_name"));
                file.setData(rs.getBytes("file_data"));
                return file;
            } else {
                throw new SQLException("File not found or expired.");
            }
        }
    }

    // Unsupported DAO methods
    public void updateFile() {
        throw new UnsupportedOperationException("Update operation is not supported.");
    }

    public void deleteFile() {
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }
}