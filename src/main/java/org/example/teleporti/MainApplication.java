package org.example.teleporti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("TelePorti");
        stage.setScene(scene);
        stage.setHeight(500.0);
        stage.setWidth(700.0);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}