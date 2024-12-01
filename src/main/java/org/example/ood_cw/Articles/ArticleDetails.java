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
        // Load the next article if available
        if (articles != null && !articles.isEmpty() && currentArticleIndex < articles.size() - 1) {
            currentArticleIndex++; // Move to the next article
            loadArticleDetails(articles.get(currentArticleIndex)); // Load the next article
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        // Load the previous article if available
        if (articles != null && !articles.isEmpty() && currentArticleIndex > 0) {
            currentArticleIndex--; // Move to the previous article
            loadArticleDetails(articles.get(currentArticleIndex)); // Load the previous article
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
    public void navigateToUserArticle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ood_cw/UserArticle.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("user Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}