package org.kursovoi.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    public void signUpButtonClicked(ActionEvent actionEvent) throws IOException {
        signUpButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signInButtonClicked(ActionEvent actionEvent) throws IOException {
        if(loginField.getText().equals("1")) {
            signInButton.getScene().getWindow().hide();
            var loader = new FXMLLoader(HelloApplication.class.getResource("admin/adminAccount.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        else if(loginField.getText().equals("2")) {
            signInButton.getScene().getWindow().hide();
            var loader = new FXMLLoader(HelloApplication.class.getResource("user/userAccount.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        else {
            AlertManager.showMessage("Вы не ввели логин и пароль!");
        }
    }
}
