package org.kursovoi.client.util.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kursovoi.client.HelloApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Presenter {

    @Autowired
    private ApplicationContext applicationContext;

    public void show(Form form) throws IOException {
        var loader = new FXMLLoader(HelloApplication.class.getResource(form.getFormUri()));
        loader.setControllerFactory(applicationContext::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
