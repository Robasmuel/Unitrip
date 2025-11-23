module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example to javafx.fxml;
    opens com.example.Controller to javafx.fxml;
    opens com.example.modelo to javafx.base;

    exports com.example;
}
