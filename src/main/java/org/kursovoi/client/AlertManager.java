package org.kursovoi.client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class AlertManager {
    public static void showMessage(String msg) {
        Stage stage = new Stage();
        AnchorPane pane = new AnchorPane();

        Label label = new Label(msg);
        AnchorPane.setTopAnchor(label, 20.0);
        AnchorPane.setLeftAnchor(label, 10.0);

        Button btn = new Button("OK");
        AnchorPane.setBottomAnchor(btn, 20.0);
        AnchorPane.setLeftAnchor(btn, 110.0);
        btn.setOnAction(event -> stage.close());
        pane.getChildren().addAll(label, btn);

        Scene scene = new Scene(pane, 250, 100);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
