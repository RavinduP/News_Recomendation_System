package org.example.ood_cw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArticleLoader {

    public static ObservableList<Article> loadArticlesFromCSV(String filePath) {
        ObservableList<Article> articles = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming CSV format: Title, Category, PublishDate
                String[] data = line.split(",");
                if (data.length == 3) {
                    String title = data[0];
                    String category = data[1];
                    String publishDate = data[2];
                    articles.add(new Article(title, category, publishDate));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
