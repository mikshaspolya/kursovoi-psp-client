package org.kursovoi.client.basic;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kursovoi.client.dto.AuthRequestDto;
import org.kursovoi.client.dto.CreateUserDto;
import org.kursovoi.client.dto.UserDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpController {

    @Autowired
    private Presenter presenter;

    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<CreateUserDto> serializer;
    @Autowired
    private ResponseDeserializer<String> errorDeserializer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField surnameField;

    @FXML
    void initialize() {

    }

    public void back(ActionEvent actionEvent) throws IOException {
        backButton.getScene().getWindow().hide();
        presenter.show(Form.MAIN);
    }

    public void signUpButtonPressed(ActionEvent actionEvent) throws IOException {
        CreateUserDto dto = CreateUserDto.builder()
                .login(loginField.getText())
                .password(passwordField.getText())
                .email(emailField.getText())
                .dateOfBirth(dateField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .name(nameField.getText())
                .surname(surnameField.getText())
                .phoneNumber(phoneField.getText())
                .repeatPassword(repeatPasswordField.getText())
                .build();
        var request = serializer.apply(dto);
        var response = messageSender.sendMessage(CommandType.CREATE_USER, request);
        if (response.contains("ERROR")) {
            AlertManager.showMessage(errorDeserializer.apply(response, String.class));
        }
        signUpButton.getScene().getWindow().hide();
        presenter.show(Form.MAIN);
    }
}
