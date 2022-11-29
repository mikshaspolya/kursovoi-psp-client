package org.kursovoi.client.user;

import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.kursovoi.client.HelloApplication;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccountController {

    @Autowired
    private Presenter presenter;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private ListView<String> accountListView;

    @FXML
    private Button addButton;

    @FXML
    private Button showButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button editButton;

    @FXML
    private Button exitButton;

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
    void initialize() {
        nameLabel.setText("Полина");
        surnameLabel.setText("Микшас");
        loginLabel.setText("polyaa");
        emailLabel.setText("mikshaspolina@gmail.com");
        phoneLabel.setText("80447597096");

        accountListView.getItems().addAll("Безымянная ****4884 08/24 10.24 p.", "name ****4884 08/25 100.24 p.");
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
    public void addButtonClicked(ActionEvent actionEvent) throws IOException {
        addButton.getScene().getWindow().hide();
        presenter.show(Form.ADD_ACCOUNT);
    }

    @FXML
    public void deleteButtonClicked(ActionEvent actionEvent) {
        final int selectedIdx = accountListView.getSelectionModel().getSelectedIndex();
        accountListView.getItems().remove(selectedIdx);
    }

    @FXML
    public void showButtonClicked(ActionEvent actionEvent) throws IOException {
        showButton.getScene().getWindow().hide();
        presenter.show(Form.SHOW_ACCOUNTS);
    }

    @FXML
    void exitButtonClicked(ActionEvent event) throws IOException {
        exitButton.getScene().getWindow().hide();
        presenter.show(Form.MAIN);
    }

    @FXML
    void editButtonClicked(ActionEvent event) throws IOException {
        editButton.getScene().getWindow().hide();
        presenter.show(Form.EDIT_USER_ACCOUNT);
    }
}
