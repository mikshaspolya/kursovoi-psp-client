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
import org.kursovoi.client.dto.AuthRequestDto;
import org.kursovoi.client.dto.UserDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageJobScheduler;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController {

    @Autowired
    private MessageJobScheduler messageJobScheduler;
    @Autowired
    private RequestSerializer<AuthRequestDto> serializer;
    @Autowired
    private ResponseDeserializer<UserDto> deserializer;
    @Autowired
    private ResponseDeserializer<String> errorDeserializer;

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
        if (!loginField.getText().isBlank() && !passwordField.getText().isBlank()) {
            AuthRequestDto dto = new AuthRequestDto(loginField.getText(), passwordField.getText());
            String response = messageJobScheduler.sendMessageJob(CommandType.AUTHENTICATE_USER, serializer.apply(dto));
            if (response.contains("ERROR")) {
                AlertManager.showMessage(errorDeserializer.apply(response));
            } else {
                UserDto user = deserializer.apply(response);
                if (user.getRole().equals("ADMIN")) {
                    signInButton.getScene().getWindow().hide();
                    var loader = new FXMLLoader(HelloApplication.class.getResource("admin/adminAccount.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } else if (user.getRole().equals("USER")) {
                    signInButton.getScene().getWindow().hide();
                    var loader = new FXMLLoader(HelloApplication.class.getResource("user/userAccount.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
            }
        } else {
            AlertManager.showMessage("Вы не ввели логин и пароль!");
        }
    }
}
