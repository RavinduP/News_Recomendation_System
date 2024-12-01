package org.example.ood_cw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Admindash {

    // Method to navigate to the User Management screen
    public void navigateToUserManagement(ActionEvent event) {
        try {
            // Load the usermanagement.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ood_cw/usermangement.fxml"));
            Parent root = loader.load();

            // Get the current stage from the button click event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("User Management");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Log the error for debugging
        }
    }

    // Navigate to the UserController.fxml (for both Add and Remove buttons)
    public void navigateToUserController(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ood_cw/usercontroll.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("User Controller");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Navigate back to Admindash.fxml
    public void navigateToAdmindash(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ood_cw/Admindash.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToContentManagement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ood_cw/contentmangement.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Content Management");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void handleCheckButton(ActionEvent event) {
    }

}