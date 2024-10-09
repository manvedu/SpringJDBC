package com.example.dbcopytool;

import java.util.Properties;

public class DatabaseCopyTool {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: DatabaseCopyTool <source_db_url> <target_db_url> <order>");
            System.out.println("order: 'direct' or 'reverse'");
            return;
        }

        String sourceDbUrl = args[0];
        String targetDbUrl = args[1];
        String order = args[2];

        // Validate order
        if (!order.equals("direct") && !order.equals("reverse")) {
            System.err.println("Order must be 'direct' or 'reverse'");
            return;
        }

        DatabaseCopyService service = new DatabaseCopyService(sourceDbUrl, targetDbUrl, order);
        service.copyDatabase();
    }
}