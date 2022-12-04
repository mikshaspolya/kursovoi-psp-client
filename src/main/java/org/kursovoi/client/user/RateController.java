package org.kursovoi.client.user;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Setter;
import org.kursovoi.client.basic.UserHolder;
import org.kursovoi.client.dto.CreateLoanOrderDto;
import org.kursovoi.client.dto.CurrencyCourseDto;
import org.kursovoi.client.dto.LoanOrderDto;
import org.kursovoi.client.sender.CommandType;
import org.kursovoi.client.sender.MessageSender;
import org.kursovoi.client.util.comparator.DateComparator;
import org.kursovoi.client.util.json.RequestSerializer;
import org.kursovoi.client.util.json.ResponseDeserializer;
import org.kursovoi.client.util.window.Form;
import org.kursovoi.client.util.window.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Component;

@Component
@Setter
public class RateController {

    @Autowired
    private Presenter presenter;
    @Autowired
    private DateComparator dateComparator;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private RequestSerializer<Long> serializer;
    @Autowired
    private RequestSerializer<CreateLoanOrderDto> serializerForLoanOrders;
    @Autowired
    private ResponseDeserializer<String> deserializer;
    @Autowired
    private ResponseDeserializer<CurrencyCourseDto> deserializerForTodayCourse;

    private List<CurrencyCourseDto> currencyCourses;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button converterButton;

    @FXML
    private ComboBox<String> currencyComboBox;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button depositButton;

    @FXML
    private Label eurToBynLabel;

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
    private LineChart<String, Number> rateLineChart;

    @FXML
    private Label rubToBynLabel;

    @FXML
    private Label usdToBynLabel;

    @FXML
    void converterButtonClicked(ActionEvent event) throws IOException {
        converterButton.getScene().getWindow().hide();
        presenter.show(Form.CONVERTER);
    }

    @FXML
    void currencyComboBoxClicked(ActionEvent event) {
        rateLineChart.getData().clear();
        var pickedCurrency = currencyComboBox.getValue();
        Map<String, Double> filteredMap = new HashMap<>();
        switch(pickedCurrency) {
            case "USD" : {
                currencyCourses.forEach(currency -> filteredMap.put(currency.getDate(), currency.getCostUsd()));
                break;
            }
            case "EUR" : {
                currencyCourses.forEach(currency -> filteredMap.put(currency.getDate(), currency.getCostEur()));
                break;
            }
            case "RUB" : {
                currencyCourses.forEach(currency -> filteredMap.put(currency.getDate(), currency.getCostRub()));
                break;
            }
        }

        xAxis.setLabel("Дата");
        XYChart.Series series = new XYChart.Series();

        series.setName(pickedCurrency);

        filteredMap.forEach((key, value) -> series.getData().add(new XYChart.Data(key, value)));

        rateLineChart.getData().add(series);
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
    void initialize() throws JsonProcessingException {
        currencyComboBox.getItems().addAll("USD", "EUR", "RUB");
        var request = serializer.apply(UserHolder.getUser().getId());
        var response =
                messageSender.sendMessage(CommandType.GET_ALL_CURRENCY_COURSES, request);

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var objectMapper = mapper.getObjectMapper();

        List<CurrencyCourseDto> list = objectMapper.readValue(response, new TypeReference<>() {});

        setCurrencyCourses(list);



        var requestForCurrencyToday = serializer.apply(UserHolder.getUser().getId());
        var responseForCurrencyToday =
                messageSender.sendMessage(CommandType.GET_CURRENCY_COURSE_FOR_TODAY, requestForCurrencyToday);
        var course = deserializerForTodayCourse.apply(responseForCurrencyToday, CurrencyCourseDto.class);
        usdToBynLabel.setText(Double.toString(course == null ? 0 : course.getCostUsd()));
        eurToBynLabel.setText(Double.toString(course == null ? 0 : course.getCostEur()));
        rubToBynLabel.setText(Double.toString(course == null ? 0 : course.getCostRub()));
    }

}
