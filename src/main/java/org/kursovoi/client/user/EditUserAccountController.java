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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditUserAccountController {

    @Autowired
    private Presenter presenter;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField editEmailField;

    @FXML
    private TextField editLoginField;

    @FXML
    private TextField editNameField;

    @FXML
    private TextField editPhoneField;

    @FXML
    private TextField editSurnameField;

    @FXML
    private Button depositButton;

    @FXML
    private Button editButton;

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
    void editButtonClicked(ActionEvent event) throws IOException {
        String name = editNameField.getText();
        String surname = editSurnameField.getText();
        String login = editLoginField.getText();
        String email = editEmailField.getText();
        String phone = editPhoneField.getText();

        //сохранение данных в бд

        myAccountButton.getScene().getWindow().hide();
        presenter.show(Form.MAIN);
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
