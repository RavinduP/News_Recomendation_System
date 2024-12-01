package org.example.ood_cw.Articles;

import javafx.beans.property.SimpleStringProperty;

public class Article {
    private SimpleStringProperty articleId;
    private SimpleStringProperty title;
    private SimpleStringProperty category;
    private SimpleStringProperty publishDate;
    private SimpleStringProperty content;  // Add content property

    // Updated constructor to include content
    public Article(String articleId, String title, String category, String publishDate, String content) {
        this.articleId = new SimpleStringProperty(articleId);
        this.title = new SimpleStringProperty(title);
        this.category = new SimpleStringProperty(category);
        this.publishDate = new SimpleStringProperty(publishDate);
        this.content = new SimpleStringProperty(content);  // Initialize content property
    }

    // Getter and Setter methods for the properties

    public String getArticleId() {
        return articleId.get();
    }

    public void setArticleId(String articleId) {
        this.articleId.set(articleId);
    }

    public SimpleStringProperty articleIdProperty() {
        return articleId;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public String getPublishDate() {
        return publishDate.get();
    }

    public void setPublishDate(String publishDate) {
        this.publishDate.set(publishDate);
    }

    public SimpleStringProperty publishDateProperty() {
        return publishDate;
    }

    public String getContent() {
        return content.get();  // Return the actual content
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }
}