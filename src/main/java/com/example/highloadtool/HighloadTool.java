package com.example.highloadtool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HighloadTool {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Configuration file path required as a parameter.");
            return;
        }

        Properties config = new Properties();
        try (FileInputStream fis = new FileInputStream(args[0])) {
            config.load(fis);
        } catch (IOException e) {
            System.err.println("Error reading configuration file: " + e.getMessage());
            return;
        }

        HighloadService service = new HighloadService(config);
        service.run();
    }
}
