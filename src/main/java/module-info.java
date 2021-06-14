module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires Core;

    opens org.example to javafx.fxml;
    exports org.example;
}