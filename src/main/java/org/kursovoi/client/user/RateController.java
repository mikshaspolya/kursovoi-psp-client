package org.kursovoi.client.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateController {

    @Autowired
    private Presenter presenter;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button converterButton;

    @FXML
    private ComboBox<String> currencyComboBox;

    @FXML
    private NumberAxis currencyLabel;

    @FXML
    private Button depositButton;

    @FXML
    private Label eurToBynLabel;

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
    private LineChart<?, ?> rateLineChart;

    @FXML
    private Label rubToBynLabel;

    @FXML
    private Label usdToBynLabel;

    @FXML
    void converterButtonClicked(ActionEvent event) throws IOException {
        converterButton.getScene().getWindow().hide();
        presenter.show(Form.CONVERTER);
    }

    @FXML
    void currencyComboBoxClicked(ActionEvent event) {

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
    void initialize() {
        currencyComboBox.getItems().addAll("USD", "BYN", "EUR", "RUB");
    }

}
