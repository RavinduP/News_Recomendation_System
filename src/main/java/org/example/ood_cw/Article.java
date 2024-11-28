package org.example.ood_cw;

import javafx.beans.property.SimpleStringProperty;

public class Article {
    private SimpleStringProperty title;
    private SimpleStringProperty category;
    private SimpleStringProperty publishDate;

    // Constructor
    public Article(String title, String category, String publishDate) {
        this.title = new SimpleStringProperty(title);
        this.category = new SimpleStringProperty(category);
        this.publishDate = new SimpleStringProperty(publishDate);
    }

    // Getter and Setter methods for the properties
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
}
