package org.kursovoi.client.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
import lombok.Setter;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.dto.AccountDto;
import org.kursovoi.client.dto.CardDto;
import org.kursovoi.client.dto.UpdateStatusDto;
import org.kursovoi.client.dto.UserDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
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
@Setter
public class ShowUserController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;
    @Autowired
    private ResponseDeserializer<String> deserializer;
    @Autowired
    private RequestSerializer<UpdateStatusDto> serializerForUpdatingStatus;

    private static UserDto user;

    private List<AccountDto> accounts;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<AccountDto> cardListView;

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
    private ComboBox<String> statusComboBox;

    @FXML
    private Label surnameLabel;

    @FXML
    private Button updateStatusButton;

    public static void setUser(UserDto user) {
        ShowUserController.user = user;
    }

    @FXML
    void initialize() throws JsonProcessingException {
        idLabel.setText(Long.toString(user.getId()));
        dateLabel.setText(user.getDateOfBirth());
        emailLabel.setText(user.getEmail());
        nameLabel.setText(user.getName());
        surnameLabel.setText(user.getSurname());
        phoneLabel.setText(user.getPhoneNumber());
        loginLabel.setText(user.getLogin());
        statusComboBox.getItems().addAll("PENDING", "BLOCKED", "ACTIVE", "EXPIRED");

        var request = serializer.apply(user.getId());
        var response = messageSender.sendMessage(CommandType.GET_ALL_ACCOUNTS_OF_USER, request);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<AccountDto> list = objectMapper.readValue(response, new TypeReference<>() {});
        setAccounts(list);
        cardListView.getItems().addAll(list);
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
        if (cardListView.getSelectionModel().getSelectedIndex() <= -1) {
            AlertManager.showMessage("Выберите счет");
            presenter.show(Form.SHOW_USER);
        } else {
            showButton.getScene().getWindow().hide();
            AccountDto selectedAccount = cardListView.getSelectionModel().getSelectedItem();
            ShowUserAccountsController.setAccount(selectedAccount);
            presenter.show(Form.SHOW_USER_ACCOUNTS);
        }
    }

    @FXML
    void updateStatusButtonClicked(ActionEvent event) throws IOException {
        myAccountButton.getScene().getWindow().hide();
        var newStatus = statusComboBox.getValue();
        UpdateStatusDto dto = UpdateStatusDto.builder()
                .newStatus(newStatus)
                .id(user.getId())
                .build();
        var request = serializerForUpdatingStatus.apply(dto);
        var response = deserializer
                .apply(messageSender.sendMessage(CommandType.UPDATE_STATUS_USER, request), String.class);
        AlertManager.showMessage(response);
        presenter.show(Form.ADMIN_ACCOUNT);
    }
}
