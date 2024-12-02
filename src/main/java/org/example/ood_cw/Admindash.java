package org.example.ood_cw;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.example.ood_cw.models.User;
import org.example.ood_cw.utils.DatabaseHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Admindash implements Initializable {
    @FXML
    private TableView<User> articleTable; // Renamed to match the existing FXML

    @FXML
    private TableColumn<User, String> colUserId;

    @FXML
    private TableColumn<User, String> colFirstName;

    @FXML
    private TableColumn<User, String> colLastName;

    @FXML
    private TableColumn<User, String> colUserName;

    @FXML
    private TableColumn<User, String> colEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add null checks
        if (colUserId == null) {
            return;
        }
        if (colFirstName == null) {
            return;
        }
        // ... similar checks for other columns

        // Set up the table columns
        colUserId.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        colUserName.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        // Load users into the table
        loadUsers();
    }

    private void loadUsers() {
        ObservableList<User> users = DatabaseHelper.getAllUsers();
        articleTable.setItems(users);
    }


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

