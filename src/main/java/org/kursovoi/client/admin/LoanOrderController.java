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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.kursovoi.client.basic.AlertManager;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.DepositOrderDto;
import org.kursovoi.client.dto.LoanOrderDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class LoanOrderController {

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
    private Button depositButton;

    @FXML
    private Button depositOrderButton;

    @FXML
    private Button loanButton;

    @FXML
    private Button loanOrderButton;

    @FXML
    private ListView<LoanOrderDto> loanOrderListView;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button rateButton;

    @FXML
    private Button showOrderButton;

    @FXML
    void initialize() throws JsonProcessingException {
        var request = serializer.apply(UserHolder.getUser().getId());
        var response = messageSender.sendMessage(CommandType.GET_ALL_PENDING_LOAN_ORDERS, request);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<LoanOrderDto> list = objectMapper.readValue(response, new TypeReference<>() {});
        loanOrderListView.getItems().addAll(list);
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
    void showOrderButtonClicked(ActionEvent event) throws IOException {
        showOrderButton.getScene().getWindow().hide();
        if (loanOrderListView.getSelectionModel().getSelectedIndex() <= -1) {
            AlertManager.showMessage("Выберите кредит");
            presenter.show(Form.LOAN_ORDER);
        } else {
            showOrderButton.getScene().getWindow().hide();
            LoanOrderDto deposit = loanOrderListView.getSelectionModel().getSelectedItem();
            ShowLoanOrderController.setOrder(deposit);
            presenter.show(Form.SHOW_LOAN_ORDER);
        }
    }
}
