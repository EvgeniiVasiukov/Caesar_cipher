module org.example.avecaesarapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.avecaesarapp to javafx.fxml;
    exports org.example.avecaesarapp;
}