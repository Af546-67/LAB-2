module com.example.aashish {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.aashish to javafx.fxml;
    exports com.example.aashish;
}