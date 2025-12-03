module PRAKTIKUM7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens application to javafx.fxml;
    opens controller to javafx.fxml;

    exports application;
    exports controller;
    exports model;
}
