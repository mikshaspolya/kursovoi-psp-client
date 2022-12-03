package org.kursovoi.client.user;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.AccountDto;
import org.kursovoi.client.dto.TransactionDto;
import org.kursovoi.client.dto.UserDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.encoder.RSACipher;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@Component
public class PaymentController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<TransactionDto> serializer;
    @Autowired
    private RequestSerializer<Long> serializerForAccounts;
    @Autowired
    private ResponseDeserializer<String> deserializer;
    @Autowired
    private ResponseDeserializer<String> errorDeserializer;
    @Autowired
    private RSACipher cipher;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button depositButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button loanButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private ComboBox<String> numOfAccountComboBox;

    @FXML
    private TextField numOfAccountField;

    @FXML
    private Button paymentButton;

    @FXML
    private Button rateButton;

    @FXML
    private Button sendButton;

    @FXML
    private TextField sumField;

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
    void rateButtonClicked(ActionEvent event) throws IOException {
        rateButton.getScene().getWindow().hide();
        presenter.show(Form.RATE);
    }

    @FXML
    void sendButtonClicked(ActionEvent event) throws IOException {
        try {
            TransactionDto dto = TransactionDto.builder()
                    .idFrom(cipher.cipher(numOfAccountComboBox.getValue()))
                    .idTo(cipher.cipher(numOfAccountField.getText()))
                    .sum(cipher.cipher(sumField.getText()))
                    .build();
            var request = serializer.apply(dto);
            var response = messageSender.sendMessage(CommandType.MAKE_TRANSACTION, request);
            if (response.contains("ERROR")) {
                AlertManager.showMessage(errorDeserializer.apply(response, String.class));
            }
            AlertManager.showMessage(deserializer.apply(response, String.class));
            sendButton.getScene().getWindow().hide();
            presenter.show(Form.SHOW_ACCOUNTS);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            AlertManager.showMessage("Ошибка шифрования! Невозможно совершить транзакцию");
        }
    }

    @FXML
    void initialize() throws JsonProcessingException {
        var request = serializerForAccounts.apply(UserHolder.getUser().getId());
        var response = messageSender.sendMessage(CommandType.GET_ALL_ACCOUNTS_OF_USER, request);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<AccountDto> list = objectMapper.readValue(response, new TypeReference<>() {
        });

        numOfAccountComboBox.getItems().addAll(list.stream().map(account -> account.getId().toString())
                .collect(Collectors.toList()));
    }
}
