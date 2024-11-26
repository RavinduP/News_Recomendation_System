package org.example.ood_cw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.example.ood_cw.utils.DatabaseHelper;

import java.io.IOException;

public class HomeController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    // Initialize the controller
    @FXML
    public void initialize() {
        // Attach event listeners to buttons
        registerButton.setOnAction(event -> handleRegisterAction());
        loginButton.setOnAction(event -> handleLoginAction());
    }

    // Handle the Register button action
    private void handleRegisterAction() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String userName = userNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validation checks
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields are required.", Alert.AlertType.ERROR);
            return;
        }

        if (!email.endsWith("@gmail.com")) {
            showAlert("Error", "Please use a valid Gmail address.", Alert.AlertType.ERROR);
            return;
        }

        if (DatabaseHelper.isEmailRegistered(email)) {
            showAlert("Error", "Email is already registered. Please log in.", Alert.AlertType.ERROR);
            return;
        }

        // Register user
        boolean success = DatabaseHelper.registerUser(firstName, lastName, userName, email, password);
        if (success) {
            showAlert("Success", "Registration successful! You can now log in.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Registration failed. Please try again.", Alert.AlertType.ERROR);
        }
    }


    // Handle the Login button action
    private void handleLoginAction() {
        try {
            // Load the Home.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = fxmlLoader.load();

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene homeScene = new Scene(homeRoot);
            stage.setScene(homeScene);

            // Optionally set the title of the stage
            stage.setTitle("Home");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load the Home page.", AlertType.ERROR);
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