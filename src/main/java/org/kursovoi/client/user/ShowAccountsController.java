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
import org.springframework.stereotype.Component;

@Component
public class ShowAccountsController {
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
        Parent root = FXMLLoader.load(getClass().getResource("addCard.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteButtonClicked(ActionEvent actionEvent) {
        final int selectedIdx = cardListView.getSelectionModel().getSelectedIndex();
        cardListView.getItems().remove(selectedIdx);
    }

    @FXML
    public void myAccountButtonClicked(ActionEvent actionEvent) throws IOException {
        myAccountButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("userAccount.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void historyButtonClicked(ActionEvent actionEvent) throws IOException {
        historyButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("history.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void paymentButtonClicked(ActionEvent actionEvent) throws IOException {
        paymentButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("payment.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loanButtonClicked(ActionEvent actionEvent) throws IOException {
        loanButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("loan.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void depositButtonClicked(ActionEvent actionEvent) throws IOException {
        depositButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("deposit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void rateButtonClicked(ActionEvent actionEvent) throws IOException {
        rateButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("rate.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
