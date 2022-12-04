package org.kursovoi.client.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.dto.*;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowLoanOrderController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;
    @Autowired
    private ResponseDeserializer<UserDto> deserializerForUser;
    @Autowired
    private ResponseDeserializer<LoanDto> deserializerForLoans;
    @Autowired
    private RequestSerializer<UpdateStatusDto> serializerForStatus;
    @Autowired
    private ResponseDeserializer<String> deserializerForStatus;

    private static LoanOrderDto order;
    private static LoanDto loan;
    private static UserDto user;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirmButton;

    @FXML
    private Label currencyLabel;

    @FXML
    private Label dateCloseLabel;

    @FXML
    private Label dateOpenLabel;

    @FXML
    private Button rejectButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button depositOrderButton;

    @FXML
    private Label fioLabel;

    @FXML
    private Label interestLabel;

    @FXML
    private Button loanButton;

    @FXML
    private Button loanOrderButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button rateButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Label sumLabel;

    public static void setOrder(LoanOrderDto order) {
        ShowLoanOrderController.order = order;
    }

    @FXML
    void initialize() {
        var request = serializer.apply(order.getIdUser());
        user = deserializerForUser
                .apply(messageSender.sendMessage(CommandType.GET_SPECIFIC_USER, request), UserDto.class);
        var requestForLoans = serializer.apply(order.getIdLoan());
        loan = deserializerForLoans
                .apply(messageSender.sendMessage(CommandType.GET_SPECIFIC_LOAN, requestForLoans), LoanDto.class);

        currencyLabel.setText(loan.getCurrency());
        fioLabel.setText(user.getName() + " " + user.getSurname());
        dateCloseLabel.setText(order.getDateOfEnd());
        dateOpenLabel.setText(order.getDateOfIssue());
        interestLabel.setText(Double.toString(loan.getInterest()));
        sumLabel.setText(Long.toString(order.getSum()));
        statusLabel.setText(order.getStatus());
    }

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException {
        UpdateStatusDto dto = UpdateStatusDto.builder()
                .id(order.getId())
                .newStatus("APPROVED")
                .build();
        var request = serializerForStatus.apply(dto);
        var response = deserializerForStatus
                .apply(messageSender.sendMessage(CommandType.UPDATE_STATUS_LOAN_ORDER, request), String.class);
        AlertManager.showMessage("Заявка успешно принята!");
        confirmButton.getScene().getWindow().hide();
        presenter.show(Form.ADMIN_ACCOUNT);
    }

    @FXML
    void rejectButtonClicked(ActionEvent event) throws IOException {
        UpdateStatusDto dto = UpdateStatusDto.builder()
                .id(order.getId())
                .newStatus("DECLINED")
                .build();
        var request = serializerForStatus.apply(dto);
        var response = deserializerForStatus
                .apply(messageSender.sendMessage(CommandType.UPDATE_STATUS_LOAN_ORDER, request), String.class);
        AlertManager.showMessage("Заявка успешно отклонена!");
        confirmButton.getScene().getWindow().hide();
        presenter.show(Form.ADMIN_ACCOUNT);
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
        loanButton.getScene().getWindow().hide();
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
}
