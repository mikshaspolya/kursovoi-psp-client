package org.kursovoi.client.admin;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.dto.AccountDto;
import org.kursovoi.client.dto.CardDto;
import org.kursovoi.client.dto.UpdateStatusDto;
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
public class ShowUserAccountsController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;
    @Autowired
    private ResponseDeserializer<String> deserializer;
    @Autowired
    private RequestSerializer<UpdateStatusDto> serializerForUpdatingStatus;

    private static AccountDto account;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label accountNumberLabel;

    @FXML
    private ListView<CardDto> cardListView;

    @FXML
    private Label currencyLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button depositButton;

    @FXML
    private Button depositOrderButton;

    @FXML
    private Button editCardStatusButton;

    @FXML
    private Button loanButton;

    @FXML
    private Button loanOrderButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button rateButton;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Label sumLabel;

    @FXML
    private Button updateStatusButton;

    public static void setAccount(AccountDto account) {
        ShowUserAccountsController.account = account;
    }

    @FXML
    void initialize() throws JsonProcessingException {
        accountNumberLabel.setText(account.getId().toString());
        dateLabel.setText(account.getDateOfIssue());
        currencyLabel.setText(account.getCurrency());
        sumLabel.setText(account.getSum().toString());

        statusComboBox.getItems().addAll("PENDING", "BLOCKED", "ACTIVE", "EXPIRED");

        var request = serializer.apply(account.getId());
        var response = messageSender.sendMessage(CommandType.GET_CARDS_OF_ACCOUNT, request);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<CardDto> list = objectMapper.readValue(response, new TypeReference<>() {});
        cardListView.getItems().addAll(list);
    }

    @FXML
    void editCardStatusButtonClicked(ActionEvent event) throws IOException {
        editCardStatusButton.getScene().getWindow().hide();
        if (cardListView.getSelectionModel().getSelectedIndex() <= -1) {
            AlertManager.showMessage("Выберите карточку");
            presenter.show(Form.SHOW_USER_ACCOUNTS);
        } else {
            var card = cardListView.getSelectionModel().getSelectedItem();
            EditCardStatusController.setCard(card);
            presenter.show(Form.EDIT_CARD_STATUS);
        }
    }

    @FXML
    void depositButtonClicked(ActionEvent event) throws IOException {
        depositButton.getScene().getWindow().hide();
        presenter.show(Form.DEPOSIT_ADMIN);
    }

    @FXML
    void depositOrderButtonClicked(ActionEvent event) throws IOException {
        depositOrderButton.getScene().getWindow().hide();
        presenter.show(Form.DEPOSIT_ORDER);
    }

    @FXML
    void loanButtonClicked(ActionEvent event) throws IOException {
        loanButton.getScene().getWindow().hide();
        presenter.show(Form.LOAN_ADMIN);
    }

    @FXML
    void loanOrderButtonClicked(ActionEvent event) throws IOException {
        loanOrderButton.getScene().getWindow().hide();
        presenter.show(Form.LOAN_ORDER);
    }

    @FXML
    void myAccountButtonClicked(ActionEvent event) throws IOException {
        myAccountButton.getScene().getWindow().hide();
        presenter.show(Form.ADMIN_ACCOUNT);
    }

    @FXML
    void rateButtonClicked(ActionEvent event) throws IOException {
        rateButton.getScene().getWindow().hide();
        presenter.show(Form.RATE_ADMIN);
    }


    @FXML
    void updateStatusButtonClicked(ActionEvent event) throws IOException {
        updateStatusButton.getScene().getWindow().hide();
        var newStatus = statusComboBox.getValue();
        UpdateStatusDto dto = UpdateStatusDto.builder()
                .newStatus(newStatus)
                .id(account.getId())
                .build();
        var request = serializerForUpdatingStatus.apply(dto);
        var response = deserializer
                .apply(messageSender.sendMessage(CommandType.UPDATE_ACCOUNT_STATUS, request), String.class);
        AlertManager.showMessage(response);
        presenter.show(Form.SHOW_USER_ACCOUNTS);
    }
}
