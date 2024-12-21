package org.example.teleporti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Panel panel = new Panel("TelePorti");
        panel.getStyleClass().add("panel-primary");
        BorderPane content = new BorderPane();
        content.setCenter(scene.getRoot());
        panel.setBody(content);
        scene.setRoot(panel);
        content.setPadding(new Insets(10));
        stage.setTitle("TelePorti");
        stage.setScene(scene);
        stage.setHeight(500.0);
        stage.setWidth(700.0);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}