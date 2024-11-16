package org.example.ood_cw.services;

import org.example.ood_cw.models.User;
import org.example.ood_cw.utils.DatabaseConnection;

import java.sql.*;

public class userservice {

    // Register a new user
    public String registerUser(String userId, String userName, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Check if userId already exists
            String checkQuery = "SELECT * FROM users WHERE userId = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setString(1, userId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    return "UserId already exists! Please try again.";
                }
            }

            // Insert new user
            String insertQuery = "INSERT INTO users (userId, userName, password) VALUES (?, ?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, userId);
                insertStmt.setString(2, userName);
                insertStmt.setString(3, password);
                insertStmt.executeUpdate();
                return "Registration successful!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "An error occurred during registration.";
        }
    }

    // Login user
    public boolean loginUser(String userId, String userName, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE userId = ? AND userName = ? AND password = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, userId);
                stmt.setString(2, userName);
                stmt.setString(3, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
