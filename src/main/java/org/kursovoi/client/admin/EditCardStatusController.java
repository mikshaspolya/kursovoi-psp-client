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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EditCardStatusController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label closeDateLabel;

    @FXML
    private Button depositButton;

    @FXML
    private Button depositOrderButton;

    @FXML
    private Label holderNameLabel;

    @FXML
    private Label issuerLabel;

    @FXML
    private Button loanButton;

    @FXML
    private Button loanOrderButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Label numberLabel;

    @FXML
    private Button rateButton;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<?> statusComboBox;

    @FXML
    private Label typeLabel;

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
    void depositOrderButtonClicked(ActionEvent event) throws IOException {
        depositOrderButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("depositOrder.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loanButtonClicked(ActionEvent event) throws IOException {
        loanButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("loan.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loanOrderButtonClicked(ActionEvent event) throws IOException {
        loanOrderButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("loanOrder.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void myAccountButtonClicked(ActionEvent event) throws IOException {
        myAccountButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminAccount.fxml"));
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
    void saveButtonClicked(ActionEvent event) throws IOException {
        saveButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminAccount.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
