package org.example.ood_cw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import org.example.ood_cw.utils.DatabaseHelper;

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


    @FXML
    private void handleCheckButton(ActionEvent event) {
        String articleId = txtArticleId.getText().trim();
        String title = txtTitle.getText().trim();

        if (articleId.isEmpty() && title.isEmpty()) {
            showError("Please enter either Article ID or Title to search.");
            return;
        }

        Article article = DatabaseHelper.getArticleByIdOrTitle(articleId, title);

        if (article != null) {
            loadArticleDetails(article);
        } else {
            showError("No article found with the provided details.");
        }
    }

    public void loadArticleDetails(Article article) {
        // Load article details into the relevant fields
        txtArticleId.setText(article.getArticleId());
        txtTitle.setText(article.getTitle());
        txtCategory.setText(article.getCategory());
        txtPublishedDate.setText(article.getPublishDate());
        txtContent.setText(article.getContent());  // Set the content in the TextArea
    }


    @FXML
    private void handleSkipButton(ActionEvent event) {
        // Logic for skipping to next article or view.
        navigateToNextArticle();
    }

    private void navigateToNextArticle() {
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        if (currentArticle != null) {
            loadArticleDetails(currentArticle);
        }
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

