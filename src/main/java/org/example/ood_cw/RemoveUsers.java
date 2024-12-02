package org.example.ood_cw;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RemoveUsers {
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
}

