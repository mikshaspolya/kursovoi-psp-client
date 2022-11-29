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
import javafx.stage.Stage;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowLoanOrderController {

    @Autowired
    private Presenter presenter;

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

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException {
        AlertManager.showMessage("Заявка успешно принята!");
        confirmButton.getScene().getWindow().hide();
        presenter.show(Form.ADMIN_ACCOUNT);
    }

    @FXML
    void rejectButtonClicked(ActionEvent event) throws IOException {
        AlertManager.showMessage("Заявка успешно отклонена!");
        rejectButton.getScene().getWindow().hide();
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
