package org.example.ood_cw.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.ood_cw.Article;

import java.sql.*;

public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/news_recommendation";
    private static final String USER = "root";
    private static final String PASSWORD = "ravindu";

    // Register a new user
    public static boolean registerUser(String firstName, String lastName, String userName, String email, String password) {
        String query = "INSERT INTO users (firstName, lastName, userName, email, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check login credentials for users
    public static boolean checkLogin(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if an email is already registered
    public static boolean isEmailRegistered(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check admin login credentials
    public static boolean checkAdminLogin(String email, String password) {
        String query = "SELECT * FROM admins WHERE email = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // If a row is returned, credentials are correct
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // In case of an error, return false
        }
    }


    private static Connection getConnection() throws SQLException {
        // Implement your DB connection here
        return DriverManager.getConnection("your_database_url", "username", "password");
    }
    public static ObservableList<Article> getArticlesByCategory(String category) {
        ObservableList<Article> articles = FXCollections.observableArrayList();
        String query;

        if (category.equalsIgnoreCase("All")) {
            query = "SELECT article_id, title, content, category, publish_date FROM articles_dataset";
        } else {
            query = "SELECT article_id, title, content, category, publish_date FROM articles_dataset WHERE category = ?";
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (!category.equalsIgnoreCase("All")) {
                preparedStatement.setString(1, category);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String articleId = resultSet.getString("article_id");
                String title = resultSet.getString("title");
                String categoryValue = resultSet.getString("category");
                String publishDate = resultSet.getString("publish_date");
                String content = resultSet.getString("content");

                // Add the article to the list
                articles.add(new Article(articleId, title, categoryValue, publishDate, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;
    }

    public static Article getArticleByIdOrTitle(String articleId, String title) {
        return null;
    }
}


