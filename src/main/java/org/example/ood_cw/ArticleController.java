package org.example.ood_cw;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
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
        colTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        colCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        colPublishDate.setCellValueFactory(cellData -> cellData.getValue().publishDateProperty());

        // Load all articles by default
        loadArticles("All");
    }

    // Event handlers for category buttons
    @FXML
    private void handleCategoryButtonAction(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String category = clickedButton.getText();
        loadArticles(category);
    }

    private void loadArticles(String category) {
        // Fetch articles based on category
        ObservableList<Article> articles = DatabaseHelper.getArticlesByCategory(category);

        // Check if articles are loaded
        if (articles.isEmpty()) {
            System.out.println("No articles found for category: " + category);
        } else {
            System.out.println("Articles loaded for category: " + category);
        }

        // Set the articles to the TableView
        articleTable.setItems(articles);
    }
}
