package org.example.ood_cw.Articles;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.example.ood_cw.utils.DatabaseHelper;

import java.io.IOException;

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
    private TextField searchTextField;  // Search input field
    @FXML
    private Button searchButton;       // Search button


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
        // Setup columns in the table
        colArticleId.setCellValueFactory(new PropertyValueFactory<>("articleId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));

        // Handle double-click on article row
        articleTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Check if double-clicked
                Article selectedArticle = articleTable.getSelectionModel().getSelectedItem();
                if (selectedArticle != null) {
                    navigateToArticleDetails(selectedArticle);
                }
            }
        });
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
        String searchQuery = searchTextField.getText().trim();

        if (searchQuery.isEmpty()) {
            showError("Please enter a search term.");
            return;
        }

        // Fetch articles from the database based on the search query
        ObservableList<Article> searchResults = DatabaseHelper.searchArticles(searchQuery);

        if (searchResults.isEmpty()) {
            showError("No articles found for the given search term.");
        } else {
            // Display the search results in the table
            articleTable.setItems(searchResults);
        }
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

            // Assuming you already have the full list of articles
            ObservableList<Article> allArticles = DatabaseHelper.getArticlesByCategory("All"); // Modify as needed
            articleDetailsController.loadArticles(allArticles); // Pass the full list of articles

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
    public void navigateToRemoveArticle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ood_cw/removearticle.fxml"));
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
    public void navigateToAddArticle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ood_cw/addarticles.fxml"));
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
}