module org.kursovoi.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.aop;
    requires spring.boot.configuration.processor;
    requires spring.boot.starter.integration;

    opens org.kursovoi.client to javafx.fxml;
    opens org.kursovoi.client.user to javafx.fxml;
    opens org.kursovoi.client.admin to javafx.fxml;
    exports org.kursovoi.client;
}