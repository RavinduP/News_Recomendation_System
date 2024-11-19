package org.example.ood_cw;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.ood_cw.services.userservice;

public class HelloController {

    @FXML
    private TextField userIdField;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;

    private userservice userService = new userservice();

    @FXML
    protected void onRegisterButtonClick() {
        String userId = userIdField.getText();
        String userName = userNameField.getText();
        String password = passwordField.getText();

        String message = userService.registerUser(userId, userName, password);
        // Show message to the user (e.g., using a dialog or label)
        System.out.println(message);
    }

    @FXML
    protected void onLoginButtonClick() {
        String userId = userIdField.getText();
        String userName = userNameField.getText();
        String password = passwordField.getText();

        boolean isLoggedIn = userService.loginUser(userId, userName, password);
        if (isLoggedIn) {
            // Load home screen or other view
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }
    }
}
