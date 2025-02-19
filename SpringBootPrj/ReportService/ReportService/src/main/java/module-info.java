module com.example.reportservice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.reportservice to javafx.fxml;
    exports com.example.reportservice;
}