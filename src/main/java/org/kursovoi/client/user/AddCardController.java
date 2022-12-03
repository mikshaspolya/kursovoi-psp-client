package org.kursovoi.client.user;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.dto.AccountDto;
import org.kursovoi.client.dto.CreateCardDto;
import org.kursovoi.client.dto.UserDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.ImmutableMessageChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class AddCardController {

    private static List<AccountDto> accounts;

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<CreateCardDto> serializer;
    @Autowired
    private ResponseDeserializer<String> deserializer;
    @Autowired
    private ResponseDeserializer<String> errorDeserializer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> accountComboBox;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> cardIssuerComboBox;

    @FXML
    private Button depositButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button loanButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private TextField nameField;

    @FXML
    private Button paymentButton;

    @FXML
    private Button rateButton;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    void initialize() {
        accounts = UserAccountController.getAccounts();
        accountComboBox.getItems()
                .addAll(accounts.stream().map(accountDto -> accountDto.getId().toString()).collect(Collectors.toList()));
        typeComboBox.getItems().addAll("DEBIT", "CREDIT");
        cardIssuerComboBox.getItems().addAll("VISA", "MASTER_CARD", "MIR", "BELCARD");
    }

    @FXML
    void addButtonClicked(ActionEvent event) throws IOException {
        CreateCardDto dto = CreateCardDto.builder()
                .cardIssuer(cardIssuerComboBox.getValue())
                .holderName(nameField.getText())
                .idAccount(Long.parseLong(accountComboBox.getValue()))
                .type(typeComboBox.getValue())
                .build();
        var request = serializer.apply(dto);
        var response = messageSender.sendMessage(CommandType.CREATE_CARD, request);
        AlertManager.showMessage(deserializer.apply(response, String.class));
        addButton.getScene().getWindow().hide();
        presenter.show(Form.SHOW_ACCOUNTS);
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
