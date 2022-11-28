package org.kursovoi.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Initialization implements ApplicationListener<UiApplication.StageReadyEvent> {

    @Autowired
    private ApplicationContext applicationContext ;

    @Override
    public void onApplicationEvent(UiApplication.StageReadyEvent event) {
        Stage stage = event.getStage();
        AnchorPane mainPane= null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            mainPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene=new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }
}
