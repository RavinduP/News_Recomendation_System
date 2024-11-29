package org.example.ood_cw;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.example.ood_cw.utils.DatabaseHelper;

public class ArticleController {

    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, String> colTitle;
    @FXML
    private TableColumn<Article, String> colCategory;
    @FXML
    private TableColumn<Article, String> colPublishDate;
    @FXML
    private TableColumn<Article, String> colArticleId;

    @FXML
    private Button btnAll;
    @FXML
    private Button btnTechnology;
    @FXML
    private Button btnHealth;
    @FXML
    private Button btnEducation;
    @FXML
    private Button btnSports;
    @FXML
    private Button btnAI;
    @FXML
    private Button btnFinance;

    @FXML
    private void initialize() {
        // Setup the columns for TableView
        colArticleId.setCellValueFactory(cellData -> cellData.getValue().articleIdProperty());
        colTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        colCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        colPublishDate.setCellValueFactory(cellData -> cellData.getValue().publishDateProperty());

        // Load all articles by default
        loadArticles("All");

        // Add row click listener to navigate to article details
        articleTable.setOnMouseClicked(this::handleRowClick);
    }

    // Event handler for clicking on a row in the table
    private void handleRowClick(MouseEvent event) {
        if (event.getClickCount() == 2) {  // Check if double-clicked
            Article selectedArticle = articleTable.getSelectionModel().getSelectedItem();
            if (selectedArticle != null) {
                navigateToArticleDetails(selectedArticle);
            }
        }
    }
    // Method to navigate to the ArticleDetails screen and pass the selected article
    private void navigateToArticleDetails(Article selectedArticle) {
        try {
            // Load the ArticleDetails FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ood_cw/ArticleDetails.fxml"));
            Parent root = loader.load();

            // Get the controller for ArticleDetails
            ArticleDetails articleDetailsController = loader.getController();
            articleDetailsController.loadArticleDetails(selectedArticle);  // Pass the selected article

            // Set up the new scene and stage
            Stage stage = (Stage) articleTable.getScene().getWindow();  // Get the current stage
            Scene scene = new Scene(root);
            stage.setScene(scene);  // Set the new scene
            stage.show();  // Show the new scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadArticles(String category) {
        // Fetch articles from the database
        ObservableList<Article> articles = DatabaseHelper.getArticlesByCategory(category);

        // Update the TableView
        articleTable.setItems(articles);
    }

    // Event handlers for category buttons
    @FXML
    private void handleCategoryButtonAction(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String category = clickedButton.getText(); // Get the button's text
        loadArticles(category); // Load articles for the category
    }
}