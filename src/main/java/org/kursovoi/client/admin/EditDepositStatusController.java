package org.kursovoi.client.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.dto.DepositDto;
import org.kursovoi.client.dto.DepositOrderDto;
import org.kursovoi.client.dto.UpdateStatusDto;
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
public class EditDepositStatusController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;
    @Autowired
    private ResponseDeserializer<UserDto> deserializerForUser;
    @Autowired
    private ResponseDeserializer<DepositDto> deserializerForDeposits;
    @Autowired
    private RequestSerializer<UpdateStatusDto> serializerForStatus;
    @Autowired
    private ResponseDeserializer<String> deserializerForStatus;

    private static DepositOrderDto order;
    private static DepositDto deposit;
    private static UserDto user;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label closeDateLabel;

    @FXML
    private Label currencyLabel;

    @FXML
    private Button depositButton;

    @FXML
    private Button depositOrderButton;

    @FXML
    private Label interestLabel;

    @FXML
    private Button loanButton;

    @FXML
    private Button loanOrderButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Label openDateLabel;

    @FXML
    private Button rateButton;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Label sumLabel;

    public static void setOrder(DepositOrderDto order) {
        EditDepositStatusController.order = order;
    }

    @FXML
    void initialize() {
/*
        var request = serializer.apply(order.getIdUser());
        user = deserializerForUser
                .apply(messageSender.sendMessage(CommandType.GET_SPECIFIC_USER, request), UserDto.class);
*/
        var requestForLoans = serializer.apply(order.getIdDeposit());
        deposit = deserializerForDeposits
                .apply(messageSender.sendMessage(CommandType.GET_SPECIFIC_LOAN, requestForLoans), DepositDto.class);

        currencyLabel.setText(deposit.getCurrency());
        closeDateLabel.setText(order.getDateOfEnd());
        openDateLabel.setText(order.getDateOfIssue());
        interestLabel.setText(Double.toString(deposit.getInterest()));
        sumLabel.setText(Long.toString(order.getSum()));
        statusComboBox.getItems().addAll("EXPIRED", "CLOSED");
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
        UpdateStatusDto dto = UpdateStatusDto.builder()
                .id(order.getId())
                .newStatus(statusComboBox.getValue())
                .build();
        var request = serializerForStatus.apply(dto);
        var response = deserializerForStatus
                .apply(messageSender.sendMessage(CommandType.UPDATE_STATUS_DEPOSIT_ORDER, request), String.class);
        AlertManager.showMessage(response);
        saveButton.getScene().getWindow().hide();
        presenter.show(Form.DEPOSIT_ADMIN);
    }
}
