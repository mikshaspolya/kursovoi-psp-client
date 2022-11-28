open module org.kursovoi.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.aop;
    requires spring.boot.starter.integration;
    requires com.google.gson;
    requires static lombok;

    requires spring.integration.core;
    requires spring.integration.ip;
    requires spring.messaging;

    /*opens org.kursovoi.client to javafx.fxml;
    opens org.kursovoi.client.user to javafx.fxml;
    opens org.kursovoi.client.admin to javafx.fxml;
    */
    exports org.kursovoi.client.util.encoder;
    exports org.kursovoi.client.util.json;
    exports org.kursovoi.client.sender;
    exports org.kursovoi.client.dto;
    exports org.kursovoi.client.basic;
    exports org.kursovoi.client;
}