package org.kursovoi.client.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.AccountDto;
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
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AddAccountController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<AccountDto> serializer;
    @Autowired
    private ResponseDeserializer<String> deserializer;
    @Autowired
    private ResponseDeserializer<String> errorDeserializer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> accountCurrencyComboBox;

    @FXML
    private Button addButton;

    @FXML
    private Button depositButton;

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
    void addButtonClicked(ActionEvent event) throws IOException {
        AccountDto dto = AccountDto.builder().holderId(UserHolder.getUser().getId()).currency(accountCurrencyComboBox.getValue()).build();
        var request = serializer.apply(dto);
        var response = messageSender.sendMessage(CommandType.CREATE_ACCOUNT, request);
        AlertManager.showMessage(deserializer.apply(response, String.class));
        addButton.getScene().getWindow().hide();
        presenter.show(Form.USER_ACCOUNT);
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

    @FXML
    void initialize() {
        accountCurrencyComboBox.getItems().addAll("USD", "BYN", "EUR", "RUB");
    }
}
