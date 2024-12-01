package org.example.ood_cw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.ood_cw.utils.DatabaseHelper;

import java.io.IOException;

public class AddArticles {

    @FXML
    private TextField txtUserId; // Article ID
    @FXML
    private TextField txtFirstName; // Title
    @FXML
    private TextField txtLastName; // Category
    @FXML
    private TextField txtUserName; // Publish Date
    @FXML
    private TextArea txtContent; // Content

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
    @FXML
    private void handleCheckButton(ActionEvent event) {
        String articleId = txtUserId.getText().trim();

        if (articleId.isEmpty()) {
            showError("Please enter the Article ID to check.");
            return;
        }

        try {
            int id = Integer.parseInt(articleId);
            boolean exists = DatabaseHelper.articleExists(String.valueOf(id));

            if (exists) {
                showSuccess("Article details are available in the database.");
            } else {
                showError("Article details are not available in the database.");
            }
        } catch (NumberFormatException e) {
            showError("Article ID must be a valid integer.");
        }
    }

    /**
     * Handles the "Add" button click event.
     * Adds the article details to the database.
     */
    @FXML
    private void handleAddButton(ActionEvent event) {
        String articleId = txtUserId.getText().trim();
        String title = txtFirstName.getText().trim();
        String category = txtLastName.getText().trim();
        String publishDate = txtUserName.getText().trim();
        String content = txtContent.getText().trim();

        // Validate input fields
        if (articleId.isEmpty() || title.isEmpty() || category.isEmpty() || publishDate.isEmpty() || content.isEmpty()) {
            showError("All fields are required. Please fill in all the details.");
            return;
        }

        try {
            int id = Integer.parseInt(articleId);
            boolean isAdded = DatabaseHelper.addArticle(id, title, content, category, publishDate);

            if (isAdded) {
                showSuccess("Article added successfully!");
                clearFields();
            } else {
                showError("Failed to add the article. Please try again.");
            }
        } catch (NumberFormatException e) {
            showError("Article ID must be a valid integer.");
        }
    }

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        txtUserId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtUserName.clear();
        txtContent.clear();
    }

    /**
     * Displays an error alert with the given message.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays a success alert with the given message.
     */
    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


