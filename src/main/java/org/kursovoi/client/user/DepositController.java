package org.kursovoi.client.user;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.*;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class DepositController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;
    @Autowired
    private RequestSerializer<CreateDepositDto> serializerForLoanOrders;
    @Autowired
    private ResponseDeserializer<String> deserializer;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private Button depositButton;

    @FXML
    private ComboBox<String> depositCurrencyComboBox;

    @FXML
    private ListView<DepositOrderDto> depositListView;

    @FXML
    private TextField depositSumField;

    @FXML
    private ComboBox<DepositDto> depositTypeComboBox;

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
    private Button sendButton;

    @FXML
    void deleteButtonClicked(ActionEvent event) {

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
    void sendButtonClicked(ActionEvent event) throws IOException {
        var newLoanOrder = CreateDepositDto.builder()
                .idDeposit(depositTypeComboBox.getValue().getId())
                .idUser(UserHolder.getUser().getId())
                .sum(Long.parseLong(depositSumField.getText()))
                .build();
        var request = serializerForLoanOrders.apply(newLoanOrder);
        var response =
                messageSender.sendMessage(CommandType.CREATE_DEPOSIT_ORDER, request);
        AlertManager.showMessage(deserializer.apply(response, String.class));
        sendButton.getScene().getWindow().hide();
        presenter.show(Form.DEPOSIT);
    }

    @FXML
    void initialize() throws JsonProcessingException {
        var requestForDepositOrders = serializer.apply(UserHolder.getUser().getId());
        var responseForDepositOrders =
                messageSender.sendMessage(CommandType.GET_DEPOSIT_ORDERS_OF_USER, requestForDepositOrders);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<DepositOrderDto> list = objectMapper.readValue(responseForDepositOrders, new TypeReference<>() {});

        depositListView.getItems().addAll(list);

        var requestForDeposits = serializer.apply(UserHolder.getUser().getId());
        var responseForDeposits =
                messageSender.sendMessage(CommandType.GET_ALL_DEPOSITS, requestForDeposits);

        List<DepositDto> listOfLoans = objectMapper.readValue(responseForDeposits, new TypeReference<>() {});

        depositTypeComboBox.getItems().addAll(listOfLoans);

    }
}
