package org.kursovoi.client.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.AccountDto;
import org.kursovoi.client.dto.UserDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class UserAccountController {

    private static List<AccountDto> accounts;

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;

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
    private ListView<AccountDto> accountListView;

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

    public static List<AccountDto> getAccounts() {
        return accounts;
    }

    @FXML
    void initialize() throws JsonProcessingException {
        UserDto user = UserHolder.getUser();
        nameLabel.setText(user.getName());
        surnameLabel.setText(user.getSurname());
        loginLabel.setText(user.getLogin());
        emailLabel.setText(user.getEmail());
        phoneLabel.setText(user.getPhoneNumber());
        var request = serializer.apply(UserHolder.getUser().getId());
        var response = messageSender.sendMessage(CommandType.GET_ALL_ACCOUNTS_OF_USER, request);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<AccountDto> list = objectMapper.readValue(response, new TypeReference<>() {});
        accounts = list;
        accountListView.getItems().addAll(list);
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
        if (accountListView.getSelectionModel().getSelectedIndex() <= -1) {
            AlertManager.showMessage("Выберите счет");
        } else {
            showButton.getScene().getWindow().hide();
            AccountDto selectedAccount = accountListView.getSelectionModel().getSelectedItem();
            ShowAccountsController.setAccount(selectedAccount);
            presenter.show(Form.SHOW_ACCOUNTS);
        }
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
