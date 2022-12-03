package org.kursovoi.client.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.CreateLoanOrderDto;
import org.kursovoi.client.dto.CurrencyCourseDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter
public class ConverterController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;
    @Autowired
    private ResponseDeserializer<CurrencyCourseDto> deserializer;

    private CurrencyCourseDto currencyCourseForToday;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button convertButton;

    @FXML
    private ComboBox<String> currencyComboBox;

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
    private TextField sumTextField;

    @FXML
    private Label sumToBynLabel;

    @FXML
    void convertButtonClicked(ActionEvent event) {
        var currency = currencyComboBox.getValue();
        double result;
        switch(currency) {
            case "USD" : {
                result = currencyCourseForToday.getCostUsd() * Double.parseDouble(sumTextField.getText());
            }
            case "EUR" : {
                result = currencyCourseForToday.getCostEur() * Double.parseDouble(sumTextField.getText());
            }
            case "RUB" : {
                result = currencyCourseForToday.getCostRub() * Double.parseDouble(sumTextField.getText());
            }
            default: result = 0;
        }
        AlertManager.showMessage(Double.toString(result));
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
        currencyComboBox.getItems().addAll("USD", "EUR", "RUB");
        var request = serializer.apply(UserHolder.getUser().getId());
        var response =
                messageSender.sendMessage(CommandType.GET_CURRENCY_COURSE_FOR_TODAY, request);
        setCurrencyCourseForToday(deserializer.apply(response, CurrencyCourseDto.class));
    }
}
