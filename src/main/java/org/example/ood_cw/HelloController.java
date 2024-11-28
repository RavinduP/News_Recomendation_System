package org.example.ood_cw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.example.ood_cw.utils.DatabaseHelper;

import java.io.IOException;

public class HelloController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    // Initialize the controller
    @FXML
    public void initialize() {
        // Optional: Add any setup code if needed
    }

    // Handle Login Button Action
    @FXML
    private void handleLoginAction() {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validation checks
        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Email and Password are required.", Alert.AlertType.ERROR);
            return;
        }

        if (!email.endsWith("@gmail.com")) {
            showAlert("Error", "Please use a valid Gmail address.", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Check if the login is for a user or an admin
            boolean isUser = DatabaseHelper.checkLogin(email, password); // Check user credentials
            boolean isAdmin = DatabaseHelper.checkAdminLogin(email, password); // Check admin credentials

            if (isUser) {
                // User logged in successfully, navigate to user dashboard
                showAlert("Success", "User login successful!", Alert.AlertType.INFORMATION);
                navigateToDashboard("UserArticle.fxml"); // Load the user dashboard
            } else if (isAdmin) {
                // Admin logged in successfully, navigate to admin dashboard
                showAlert("Success", "Admin login successful!", Alert.AlertType.INFORMATION);
                navigateToDashboard("Admindash.fxml"); // Load the admin dashboard
            } else {
                // Invalid credentials for both user and admin
                showAlert("Error", "Invalid email or password. Please try again.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred during login. Please try again later.", AlertType.ERROR);
        }
    }

    // Handle Create Account Button Action
    @FXML
    private void handleCreateAccountAction() {
        try {
            // Load the Register.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent registerRoot = fxmlLoader.load();

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene registerScene = new Scene(registerRoot);
            stage.setScene(registerScene);

            // Optionally set the title of the stage
            stage.setTitle("Register");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load the registration screen.", AlertType.ERROR);
        }
    }

    // Handle Forgot Password Button Action
    @FXML
    private void handleForgotPasswordAction() {
        // Logic for password recovery
        showAlert("Info", "Forgot Password button clicked.", AlertType.INFORMATION);
    }

    // Utility method to navigate to a specific dashboard based on user/admin login
    private void navigateToDashboard(String fxmlFile) {
        try {
            // Load the corresponding FXML file (either user or admin dashboard)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent dashboardRoot = fxmlLoader.load();

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene dashboardScene = new Scene(dashboardRoot);
            stage.setScene(dashboardScene);

            // Optionally set the title of the stage
            if (fxmlFile.equals("UserArticle.fxml")) {
                stage.setTitle("User Dashboard");
            } else if (fxmlFile.equals("Admindash.fxml")) {
                stage.setTitle("Admin Dashboard");
            }

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load the dashboard screen.", Alert.AlertType.ERROR);
        }
    }

    // Utility method to show alerts
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
