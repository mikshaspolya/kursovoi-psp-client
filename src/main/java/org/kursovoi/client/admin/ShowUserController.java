package org.kursovoi.client.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ShowUserController {

    @Autowired
    private Presenter presenter;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<?> cardListView;

    @FXML
    private Label dateLabel;

    @FXML
    private Button depositButton;

    @FXML
    private Button depositOrderButton;

    @FXML
    private Label emailLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Button loanButton;

    @FXML
    private Button loanOrderButton;

    @FXML
    private Label loginLabel;

    @FXML
    private Button myAccountButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Button rateButton;

    @FXML
    private Button showButton;

    @FXML
    private ComboBox<?> statusComboBox;

    @FXML
    private Label surnameLabel;

    @FXML
    private Button updateStatusButton;

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
    void rateButtonClicked(ActionEvent event) throws IOException {
        rateButton.getScene().getWindow().hide();
        presenter.show(Form.RATE_ADMIN);
    }

    @FXML
    void showButtonClicked(ActionEvent event) throws IOException {
        showButton.getScene().getWindow().hide();
        presenter.show(Form.SHOW_USER_ACCOUNTS);
    }

    @FXML
    void updateStatusButtonClicked(ActionEvent event) throws IOException {
        myAccountButton.getScene().getWindow().hide();
        presenter.show(Form.ADMIN_ACCOUNT);
    }
}
