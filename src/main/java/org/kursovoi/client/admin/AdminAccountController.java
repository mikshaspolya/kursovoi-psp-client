package org.kursovoi.client.admin;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.Setter;
import org.kursovoi.client.HelloApplication;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.AccountDto;
import org.kursovoi.client.dto.UserDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.user.ShowAccountsController;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Component;

@Component
@Setter
public class AdminAccountController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;

    private List<UserDto> listOfUsers;

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
    private Label emailLabel;

    @FXML
    private Button exitButton;

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
    private Label surnameLabel;

    @FXML
    private ListView<UserDto> userListView;

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
        presenter.show(Form.EDIT_ADMIN_ACCOUNT);
    }

    @FXML
    void exitButtonClicked(ActionEvent event) throws IOException {
        exitButton.getScene().getWindow().hide();
        presenter.show(Form.MAIN);
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
    void rateButtonClicked(ActionEvent event) throws IOException {
        rateButton.getScene().getWindow().hide();
        presenter.show(Form.RATE_ADMIN);
    }

    @FXML
    void showButtonClicked(ActionEvent event) throws IOException {
        showButton.getScene().getWindow().hide();
        if (userListView.getSelectionModel().getSelectedIndex() <= -1) {
            AlertManager.showMessage("Выберите пользователя");
            presenter.show(Form.ADMIN_ACCOUNT);
        } else {
            showButton.getScene().getWindow().hide();
            UserDto selectedUser = userListView.getSelectionModel().getSelectedItem();
            ShowUserController.setUser(selectedUser);
            presenter.show(Form.SHOW_USER);
        }
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
        var response = messageSender.sendMessage(CommandType.GET_ALL_USERS, request);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<UserDto> list = objectMapper.readValue(response, new TypeReference<>() {});
        setListOfUsers(list);
        userListView.getItems().addAll(list);

    }
}
