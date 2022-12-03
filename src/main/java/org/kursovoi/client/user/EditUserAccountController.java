package org.kursovoi.client.user;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.AccountDto;
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
public class EditUserAccountController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<UserDto> serializer;
    @Autowired
    private ResponseDeserializer<String> deserializer;
    @Autowired
    private ResponseDeserializer<String> errorDeserializer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField editEmailField;

    @FXML
    private TextField editLoginField;

    @FXML
    private TextField editNameField;

    @FXML
    private TextField editPhoneField;

    @FXML
    private TextField editSurnameField;

    @FXML
    private Button depositButton;

    @FXML
    private Button editButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button loanButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button paymentButton;

    @FXML
    private Button rateButton;

    @FXML
    void initialize() {
        var user = UserHolder.getUser();
        editNameField.setText(user.getName());
        editSurnameField.setText(user.getSurname());
        editLoginField.setText(user.getLogin());
        editEmailField.setText(user.getEmail());
        editPhoneField.setText(user.getPhoneNumber());
    }


    @FXML
    void editButtonClicked(ActionEvent event) throws IOException {
        String name = editNameField.getText();
        String surname = editSurnameField.getText();
        String login = editLoginField.getText();
        String email = editEmailField.getText();
        String phone = editPhoneField.getText();

        UserDto dto = UserDto.builder()
                .name(name)
                .surname(surname)
                .login(login)
                .email(email)
                .phoneNumber(phone)
                .id(UserHolder.getUser().getId())
                .build();

        String request = serializer.apply(dto);
        String response = messageSender.sendMessage(CommandType.UPDATE_USER, request);
        AlertManager.showMessage(deserializer.apply(response, String.class));

        myAccountButton.getScene().getWindow().hide();
        presenter.show(Form.MAIN);
    }

    @FXML
    void depositButtonClicked(ActionEvent event) throws IOException {
        depositButton.getScene().getWindow().hide();
        presenter.show(Form.DEPOSIT);
    }

    @FXML
    void historyButtonClicked(ActionEvent event) throws IOException {
        historyButton.getScene().getWindow().hide();
        presenter.show(Form.HISTORY);
    }

    @FXML
    void loanButtonClicked(ActionEvent event) throws IOException {
        loanButton.getScene().getWindow().hide();
        presenter.show(Form.LOAN);
    }

    @FXML
    void myAccountButtonClicked(ActionEvent event) throws IOException {
        myAccountButton.getScene().getWindow().hide();
        presenter.show(Form.USER_ACCOUNT);
    }

    @FXML
    void paymentButtonClicked(ActionEvent event) throws IOException {
        paymentButton.getScene().getWindow().hide();
        presenter.show(Form.PAYMENT);
    }

    @FXML
    void rateButtonClicked(ActionEvent event) throws IOException {
        rateButton.getScene().getWindow().hide();
        presenter.show(Form.RATE);
    }
}
