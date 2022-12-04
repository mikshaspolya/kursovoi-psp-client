package org.kursovoi.client.admin;

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
public class AdminLoanController {

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
    private Button editLoanStatusButton;

    @FXML
    private Button loanButton;

    @FXML
    private ListView<LoanOrderDto> loanListView;

    @FXML
    private Button loanOrderButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button rateButton;

    @FXML
    void initialize() throws JsonProcessingException {
        var request = serializer.apply(UserHolder.getUser().getId());
        var response = messageSender.sendMessage(CommandType.GET_ALL_LOANS_ORDERS, request);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<LoanOrderDto> list = objectMapper.readValue(response, new TypeReference<>() {});
        var filteredList = list.stream()
                .filter(loan -> !loan.getStatus().equals("PENDING")).collect(Collectors.toList());
        loanListView.getItems().addAll(filteredList);
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
    void editLoanStatusButtonClicked(ActionEvent event) throws IOException {
        editLoanStatusButton.getScene().getWindow().hide();
        if (loanListView.getSelectionModel().getSelectedIndex() <= -1) {
            AlertManager.showMessage("Выберите кредит");
            presenter.show(Form.LOAN_ADMIN);
        } else {
            LoanOrderDto loan = loanListView.getSelectionModel().getSelectedItem();
            EditLoanStatusController.setOrder(loan);
            presenter.show(Form.EDIT_LOAN_STATUS);
        }
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
}
