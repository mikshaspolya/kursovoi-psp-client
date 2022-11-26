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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.kursovoi.client.HelloApplication;

public class UserAccountController {
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
        Parent root = FXMLLoader.load(getClass().getResource("deposit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editButtonClicked(ActionEvent event) throws IOException {
        editButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("editUserAccount.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void exitButtonClicked(ActionEvent event) throws IOException {
        exitButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("main.fxml"));
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
    void loanButtonClicked(ActionEvent event) throws IOException {
        loanButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("loan.fxml"));
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

        /*final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(rateButton.getScene().getWindow());
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();*/
    }

    @FXML
    public void addButtonClicked(ActionEvent actionEvent) throws IOException {
        addButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("addAccount.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteButtonClicked(ActionEvent actionEvent) {
        final int selectedIdx = accountListView.getSelectionModel().getSelectedIndex();
        accountListView.getItems().remove(selectedIdx);
    }

    @FXML
    public void showButtonClicked(ActionEvent actionEvent) throws IOException {
        showButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("showAccounts.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
