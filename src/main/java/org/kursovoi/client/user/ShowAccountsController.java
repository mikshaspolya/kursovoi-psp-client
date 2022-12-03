package org.kursovoi.client.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.Setter;
import org.kursovoi.client.dto.AccountDto;
import org.kursovoi.client.dto.CardDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ShowAccountsController {

    private static AccountDto account;

    @Autowired
    private Presenter presenter;

    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label accountNumberLabel;

    @FXML
    private Button addButton;

    @FXML
    private ListView<CardDto> cardListView;

    @FXML
    private Label currencyLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button deleteButton;

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
    private Label statusLabel;

    @FXML
    private Label sumLabel;

    public static void setAccount(AccountDto account) {
        ShowAccountsController.account = account;
    }

    @FXML
    void initialize() throws JsonProcessingException {
        accountNumberLabel.setText(account.getId().toString());
        dateLabel.setText(account.getDateOfIssue());
        currencyLabel.setText(account.getCurrency());
        statusLabel.setText(account.getStatus());
        sumLabel.setText(account.getSum().toString());

        var request = serializer.apply(account.getId());
        var response = messageSender.sendMessage(CommandType.GET_CARDS_OF_ACCOUNT, request);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<CardDto> list = objectMapper.readValue(response, new TypeReference<>() {});

        cardListView.getItems().addAll(list);
    }

    @FXML
    public void addButtonClicked(ActionEvent actionEvent) throws IOException {
        addButton.getScene().getWindow().hide();
        presenter.show(Form.ADD_CARD);
    }

    @FXML
    public void deleteButtonClicked(ActionEvent actionEvent) {
        final int selectedIdx = cardListView.getSelectionModel().getSelectedIndex();
        cardListView.getItems().remove(selectedIdx);
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
