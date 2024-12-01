package org.example.ood_cw.Articles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.ood_cw.Articles.Article;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArticleLoader {

    public static ObservableList<Article> loadArticlesFromCSV(String filePath) {
        ObservableList<Article> articles = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming CSV format: ArticleId, Title, Category, PublishDate, Content
                String[] data = line.split(",");
                if (data.length == 5) { // Ensure there are exactly 5 fields
                    String articleId = data[0];
                    String title = data[1];
                    String category = data[2];
                    String publishDate = data[3];
                    String content = data[4]; // Read the 'content' field from CSV
                    articles.add(new Article(articleId, title, category, publishDate, content));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
