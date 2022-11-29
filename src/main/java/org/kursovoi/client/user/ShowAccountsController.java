package org.kursovoi.client.user;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAccountsController {

    @Autowired
    private Presenter presenter;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label accountNumberLabel;

    @FXML
    private Button addButton;

    @FXML
    private ListView<String> cardListView;

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

    @FXML
    void initialize() {
        accountNumberLabel.setText("2");
        dateLabel.setText("21.11.2022");
        currencyLabel.setText("USD");
        statusLabel.setText("active");
        sumLabel.setText("2000");

        cardListView.getItems().addAll("Безымянная ****4884 08/24 10.24 p.", "name ****4884 08/25 100.24 p.");
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
