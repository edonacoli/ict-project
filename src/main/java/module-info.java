module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.test to javafx.fxml;
    exports com.example.test;
    exports com.example.test.Repository;
    opens com.example.test.Repository to javafx.fxml;
}