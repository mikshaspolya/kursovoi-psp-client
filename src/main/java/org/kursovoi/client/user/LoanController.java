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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanController {

    @Autowired
    private Presenter presenter;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button depositButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button loanButton;

    @FXML
    private ListView<?> loanListView;

    @FXML
    private TextField loanSumField;

    @FXML
    private ComboBox<String> loanCurrencyComboBox;

    @FXML
    private ComboBox<String> loanTypeComboBox;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button paymentButton;

    @FXML
    private Button rateButton;

    @FXML
    private Button sendButton;

    @FXML
    void depositButtonClicked(ActionEvent event) throws IOException {
        depositButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("deposit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void historyButtonClicked(ActionEvent event) throws IOException {
        historyButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("history.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void myAccountButtonClicked(ActionEvent event) throws IOException {
        myAccountButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("userAccount.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void paymentButtonClicked(ActionEvent event) throws IOException {
        paymentButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("payment.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void rateButtonClicked(ActionEvent event) throws IOException {
        rateButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("rate.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void sendButtonClicked(ActionEvent event) {

    }
}
