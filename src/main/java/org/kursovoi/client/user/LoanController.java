package org.kursovoi.client.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.CreateLoanOrderDto;
import org.kursovoi.client.dto.LoanDto;
import org.kursovoi.client.dto.LoanOrderDto;
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
public class LoanController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;
    @Autowired
    private RequestSerializer<CreateLoanOrderDto> serializerForLoanOrders;
    @Autowired
    private ResponseDeserializer<String> deserializer;

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
    private ListView<LoanOrderDto> loanListView;

    @FXML
    private TextField loanSumField;

    @FXML
    private ComboBox<String> loanCurrencyComboBox;

    @FXML
    private ComboBox<LoanDto> loanTypeComboBox;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button paymentButton;

    @FXML
    private Button rateButton;

    @FXML
    private Button sendButton;

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
    void rateButtonClicked(ActionEvent event) throws IOException {
        rateButton.getScene().getWindow().hide();
        presenter.show(Form.RATE);
    }

    @FXML
    void myAccountButtonClicked(ActionEvent event) throws IOException {
        myAccountButton.getScene().getWindow().hide();
        presenter.show(Form.USER_ACCOUNT);
    }

    @FXML
    void paymentButtonClicked(ActionEvent event) throws IOException {
        paymentButton.getScene().getWindow().hide();
        presenter.show(Form.PAYMENT);
    }

    @FXML
    void sendButtonClicked(ActionEvent event) throws IOException {
        var newLoanOrder = CreateLoanOrderDto.builder()
                .idLoan(loanTypeComboBox.getValue().getId())
                .idUser(UserHolder.getUser().getId())
                .sum(Long.parseLong(loanSumField.getText()))
                .build();
        var request = serializerForLoanOrders.apply(newLoanOrder);
        var response =
                messageSender.sendMessage(CommandType.CREATE_LOAN_ORDER, request);
        AlertManager.showMessage(deserializer.apply(response, String.class));
        sendButton.getScene().getWindow().hide();
        presenter.show(Form.LOAN);
    }

    @FXML
    void initialize() throws JsonProcessingException {
        var requestForLoanOrders = serializer.apply(UserHolder.getUser().getId());
        var responseForLoanOrders =
                messageSender.sendMessage(CommandType.GET_LOAN_ORDERS_OF_USER, requestForLoanOrders);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<LoanOrderDto> list = objectMapper.readValue(responseForLoanOrders, new TypeReference<>() {});

        loanListView.getItems().addAll(list);

        var requestForLoans = serializer.apply(UserHolder.getUser().getId());
        var responseForLoans =
                messageSender.sendMessage(CommandType.GET_ALL_LOANS, requestForLoans);

        List<LoanDto> listOfLoans = objectMapper.readValue(responseForLoans, new TypeReference<>() {});

        loanTypeComboBox.getItems().addAll(listOfLoans);

    }
}
