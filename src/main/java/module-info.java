module org.kursovoi.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.kursovoi.client to javafx.fxml;
    opens org.kursovoi.client.user to javafx.fxml;
    opens org.kursovoi.client.admin to javafx.fxml;
    exports org.kursovoi.client;
}