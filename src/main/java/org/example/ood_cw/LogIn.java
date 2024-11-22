package org.example.ood_cw;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LogIn extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load background image
        Image backgroundImage = new Image("src/main/resources/images/img1.jpg"); // Update the path to your image file
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(600); // Adjust width as needed
        backgroundImageView.setFitHeight(400); // Adjust height as needed
        backgroundImageView.setPreserveRatio(false);

        // Create login form container
        VBox loginForm = new VBox(15);
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setPadding(new Insets(20));
        loginForm.setPrefWidth(300); // Width of the login box
        loginForm.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 15px;");

        // Create "Login" title label
        Label loginLabel = new Label("Login");
        loginLabel.setFont(new Font("Arial", 28));
        loginLabel.setTextFill(Color.BLACK);

        // Email field and label
        Label emailLabel = new Label("Email Address");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        // Password field and label
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // Forgot password button
        Button forgotPasswordButton = new Button("Forgot Password?");
        forgotPasswordButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #007bff; -fx-font-size: 12px;");

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(120);
        loginButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14px;");

        // Create new account button
        Button createAccountButton = new Button("Create New Account");
        createAccountButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #007bff; -fx-font-size: 12px;");

        // Add all elements to the form
        loginForm.getChildren().addAll(
                loginLabel,
                emailLabel, emailField,
                passwordLabel, passwordField,
                forgotPasswordButton,
                loginButton,
                createAccountButton
        );

        // Center the form in a StackPane
        StackPane mainPane = new StackPane();
        mainPane.getChildren().addAll(backgroundImageView, loginForm);
        StackPane.setAlignment(loginForm, Pos.CENTER);

        // Set up the scene and stage
        Scene scene = new Scene(mainPane, 600, 400);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
