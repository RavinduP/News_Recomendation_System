module org.example.ood_cw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.ood_cw to javafx.fxml;
    exports org.example.ood_cw;
    exports org.example.ood_cw.Articles;
    opens org.example.ood_cw.Articles to javafx.fxml;
}