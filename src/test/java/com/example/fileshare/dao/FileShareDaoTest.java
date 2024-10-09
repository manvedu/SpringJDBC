package com.example.fileshare.dao;

import com.example.fileshare.dao.FileShareDao;
import com.example.fileshare.model.FileRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileShareDaoTest {

    private final FileShareDao fileShareDao = new FileShareDao();

    @Test
    public void testSaveAndRetrieveFile() throws SQLException, IOException {
        String fileName = "large_test_file.txt";
        byte[] fileData = Files.readAllBytes(Paths.get("src/test/java/com/example/fileshare/files/large_test_file.txt"));

        Timestamp expiryDate = new Timestamp(System.currentTimeMillis() + 3600 * 1000); // Expires in 1 hour

        // Save file to DB
        Long fileId = fileShareDao.saveFile(fileName, fileData, expiryDate);
        assertNotNull(fileId);

        // Retrieve file from DB
        FileRecord retrievedFile = fileShareDao.retrieveFile(fileId);
        assertNotNull(retrievedFile);
        assertEquals(fileName, retrievedFile.getName());
        assertEquals(fileData.length, retrievedFile.getData().length);
    }
}
