package org.kursovoi.client.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.CurrencyCourseDto;
import org.kursovoi.client.dto.UpdateCurrencyCourseDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminRateController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;
    @Autowired
    private RequestSerializer<UpdateCurrencyCourseDto> serializerForCurrency;
    @Autowired
    private ResponseDeserializer<String> deserializerForCourse;
    @Autowired
    private ResponseDeserializer<CurrencyCourseDto> deserializerForTodayCourse;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button depositButton;

    @FXML
    private Button depositOrderButton;

    @FXML
    private Label eurToBynLabel;

    @FXML
    private TextField eurToBynTextField;

    @FXML
    private Button loanButton;

    @FXML
    private Button loanOrderButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button rateButton;

    @FXML
    private Label rubToBynLabel;

    @FXML
    private TextField rubToBynTextField;

    @FXML
    private Button updateRateButton;

    @FXML
    private Label usdToBynLabel;

    @FXML
    private TextField usdToBynTextField;

    @FXML
    void initialize() {
        var requestForCurrencyToday = serializer.apply(UserHolder.getUser().getId());
        var responseForCurrencyToday =
                messageSender.sendMessage(CommandType.GET_CURRENCY_COURSE_FOR_TODAY, requestForCurrencyToday);
        var course = deserializerForTodayCourse.apply(responseForCurrencyToday, CurrencyCourseDto.class);
        usdToBynLabel.setText(Double.toString(course == null ? 0 : course.getCostUsd()));
        eurToBynLabel.setText(Double.toString(course == null ? 0 : course.getCostEur()));
        rubToBynLabel.setText(Double.toString(course == null ? 0 : course.getCostRub()));
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
    void updateRateButtonClicked(ActionEvent event) throws IOException {
        UpdateCurrencyCourseDto dto = UpdateCurrencyCourseDto.builder()
                .costUsd(Double.parseDouble(usdToBynTextField.getText()))
                .costEur(Double.parseDouble(eurToBynTextField.getText()))
                .costRub(Double.parseDouble(rubToBynTextField.getText()))
                .build();
        var request = serializerForCurrency.apply(dto);
        var response = deserializerForCourse
                .apply(messageSender.sendMessage(CommandType.UPDATE_CURRENCY_COURSE, request), String.class);
        AlertManager.showMessage(response);
        updateRateButton.getScene().getWindow().hide();
        presenter.show(Form.RATE_ADMIN);
    }
}
