package org.example.ood_cw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.ood_cw.utils.DatabaseHelper;

import java.io.IOException;

public class RemoveArticles {

    @FXML
    private TextField txtlabelArticleid;
    @FXML
    private Label lblMessage; // A label to display messages to the admin (like "Article is available" or "Article not found")
    @FXML
    private Button btnRemove;

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
    public void handleCheckButtonAction(ActionEvent event) {
        String articleId = txtlabelArticleid.getText().trim();

        if (articleId.isEmpty()) {
            lblMessage.setText("Please enter a valid Article ID.");
            return;
        }

        boolean exists = DatabaseHelper.articleExists(articleId);
        if (exists) {
            // Show an information alert if the article is available
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Article Available");
            alert.setHeaderText(null);  // Optional: You can add a header text
            alert.setContentText("The article is available.");
            alert.showAndWait();
        } else {
            // Show an error alert if the article is not found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Article Not Found");
            alert.setHeaderText(null);  // Optional: You can add a header text
            alert.setContentText("The article with the given ID is not available.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleRemoveButtonAction(ActionEvent event) {
        String articleId = txtlabelArticleid.getText().trim();

        if (articleId.isEmpty()) {
            lblMessage.setText("Please enter a valid Article ID.");
            return;
        }

        boolean exists = DatabaseHelper.articleExists(articleId);
        if (exists) {
            boolean success = DatabaseHelper.removeArticle(articleId);
            if (success) {
                // Show a success alert after removing the article
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Article Removed");
                alert.setHeaderText(null);
                alert.setContentText("The article has been successfully removed.");
                alert.showAndWait();
            } else {
                // Show an error alert if removal failed
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Removal Failed");
                alert.setHeaderText(null);
                alert.setContentText("There was an error removing the article.");
                alert.showAndWait();
            }
        } else {
            // Show an error alert if the article doesn't exist
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Article Not Found");
            alert.setHeaderText(null);
            alert.setContentText("The article with the given ID is not found.");
            alert.showAndWait();
        }
    }
}