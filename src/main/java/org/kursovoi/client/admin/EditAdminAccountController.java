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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditAdminAccountController {

    @Autowired
    private Presenter presenter;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button depositButton;

    @FXML
    private Button depositOrderButton;

    @FXML
    private Button editButton;

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
    private Button loanButton;

    @FXML
    private Button loanOrderButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button rateButton;

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
    void editButtonClicked(ActionEvent event) throws IOException {
        editButton.getScene().getWindow().hide();
        presenter.show(Form.ADMIN_ACCOUNT);
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

}
