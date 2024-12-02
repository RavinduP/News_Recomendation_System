package org.example.ood_cw.Articles;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.example.ood_cw.Articles.Article;
import org.example.ood_cw.utils.DatabaseHelper;

import java.io.IOException;

public class ArticleDetails {

    @FXML
    private TextField txtArticleId;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtPublishedDate;
    @FXML
    private TextArea txtContent;

    private Article currentArticle;

    private ObservableList<Article> articles;
    private int currentArticleIndex = 0; // Track the current article index



    @FXML
    private void handleCheckButton(ActionEvent event) {
        String articleId = txtArticleId.getText().trim();
        String title = txtTitle.getText().trim();

        // Ensure at least one of the fields is filled
        if (articleId.isEmpty() && title.isEmpty()) {
            showError("Please enter an Article ID or Title.");
            return;
        }

        // Fetch the article from the database
        Article article = DatabaseHelper.getArticleByIdOrTitle(articleId, title);

        // Load the article details into the form
        loadArticleDetails(article);
    }

    private void loadArticleDetails(Article article) {
        if (article != null) {
            txtArticleId.setText(article.getArticleId());
            txtTitle.setText(article.getTitle());
            txtCategory.setText(article.getCategory());
            txtPublishedDate.setText(article.getPublishDate());
            txtContent.setText(article.getContent());
        } else {
            showError("No article found for the given ID or Title.");
        }
    }
    // Method to load articles into the controller from a list
    public void loadArticles(ObservableList<Article> articleList) {
        this.articles = articleList;
        if (articles != null && !articles.isEmpty()) {
            loadArticleDetails(articles.get(currentArticleIndex)); // Load the first article by default
        }
    }


    @FXML
    private void handleSkipButton(ActionEvent event) {
        if (articles == null || articles.isEmpty()) {
            showError("No articles available to navigate.");
            return;
        }

        // Get the current article ID
        String currentArticleId = txtArticleId.getText().trim();

        // Find the next article by ID
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getArticleId().equals(currentArticleId) && i < articles.size() - 1) {
                // Load the next article
                loadArticleDetails(articles.get(i + 1));
                return;
            }
        }

        showError("You are already viewing the last article.");
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        if (articles == null || articles.isEmpty()) {
            showError("No articles available to navigate.");
            return;
        }

        // Get the current article ID
        String currentArticleId = txtArticleId.getText().trim();

        // Find the previous article by ID
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getArticleId().equals(currentArticleId) && i > 0) {
                // Load the previous article
                loadArticleDetails(articles.get(i - 1));
                return;
            }
        }

        showError("You are already viewing the first article.");
    }
    public void navigateToUserArticle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ood_cw/UserArticle.fxml"));
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

    private void navigateToNextArticle() {
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setArticleDetails(Article article) {
        txtArticleId.setText(String.valueOf(article.getArticleId()));
        txtTitle.setText(article.getTitle());
        txtCategory.setText(article.getCategory());
        txtPublishedDate.setText(article.getPublishDate().toString());
        txtContent.setText(article.getContent());
    }

}