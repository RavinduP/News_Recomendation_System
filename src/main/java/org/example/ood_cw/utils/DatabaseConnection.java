package org.example.ood_cw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/news_recommendation";
    private static final String USER = "root";
    private static final String PASSWORD = "ravindu";

    public static Connection getConnection() throws SQLException {
        try {
            // Ensure that the JDBC driver is registered
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
}
