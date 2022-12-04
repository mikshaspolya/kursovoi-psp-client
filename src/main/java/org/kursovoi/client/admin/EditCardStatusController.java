package org.kursovoi.client.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.dto.CardDto;
import org.kursovoi.client.dto.ChangeStatusOfCardDto;
import org.kursovoi.client.dto.UpdateStatusDto;
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
public class EditCardStatusController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private ResponseDeserializer<String> deserializer;
    @Autowired
    private RequestSerializer<ChangeStatusOfCardDto> serializer;

    private static CardDto card;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label closeDateLabel;

    @FXML
    private Button depositButton;

    @FXML
    private Button depositOrderButton;

    @FXML
    private Label holderNameLabel;

    @FXML
    private Label issuerLabel;

    @FXML
    private Button loanButton;

    @FXML
    private Button loanOrderButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Label numberLabel;

    @FXML
    private Button rateButton;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Label typeLabel;

    public static void setCard(CardDto card) {
        EditCardStatusController.card = card;
    }

    @FXML
    void initialize() throws JsonProcessingException {
        numberLabel.setText(Long.toString(card.getId()));
        typeLabel.setText(card.getType());
        issuerLabel.setText(card.getCardIssuer());
        holderNameLabel.setText(card.getHolderName());
        closeDateLabel.setText(card.getDateOfExpire());
        statusComboBox.getItems().addAll("PENDING", "BLOCKED", "ACTIVE", "EXPIRED");
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
    void saveButtonClicked(ActionEvent event) throws IOException {
        saveButton.getScene().getWindow().hide();
        var newStatus = statusComboBox.getValue();
        ChangeStatusOfCardDto dto = ChangeStatusOfCardDto.builder()
                .newStatus(newStatus)
                .cardId(card.getId())
                .build();
        var request = serializer.apply(dto);
        var response = deserializer
                .apply(messageSender.sendMessage(CommandType.UPDATE_STATUS_OF_CARD, request), String.class);
        AlertManager.showMessage(response);
        presenter.show(Form.ADMIN_ACCOUNT);
    }
}
