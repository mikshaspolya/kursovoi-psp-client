package org.kursovoi.client.basic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.kursovoi.client.dto.AuthRequestDto;
import org.kursovoi.client.dto.UserDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
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
        presenter.show(Form.SIGN_UP);
    }

    @FXML
    public void signInButtonClicked(ActionEvent actionEvent) throws IOException {
        if (!loginField.getText().isBlank() && !passwordField.getText().isBlank()) {
            AuthRequestDto dto = new AuthRequestDto(loginField.getText(), passwordField.getText());
            String response = messageSender.sendMessage(CommandType.AUTHENTICATE_USER, serializer.apply(dto));
            if (response.contains("ERROR")) {
                AlertManager.showMessage(errorDeserializer.apply(response, String.class));
            } else {
                UserDto user = deserializer.apply(response, UserDto.class);
                UserHolder.setUser(user);
                if (user.getRole().equals("ADMIN")) {
                    signInButton.getScene().getWindow().hide();
                    presenter.show(Form.ADMIN_ACCOUNT);
                } else if (user.getRole().equals("USER")) {
                    signInButton.getScene().getWindow().hide();
                    presenter.show(Form.USER_ACCOUNT);
                }
            }
        } else {
            AlertManager.showMessage("Вы не ввели логин и пароль!");
        }
    }
}
