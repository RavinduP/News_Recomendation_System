package org.example.ood_cw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
    private static final String URL = "jdbc:mysql://localhost:3306/news_recommendation";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "ravindu"; // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
