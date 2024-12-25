// MainApplication.java
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
        Scene scene = new Scene(fxmlLoader.load());
        Panel panel = new Panel();
        String css = this.getClass().getResource("Styles/Styles.css").toExternalForm();
        panel.getStylesheets().add(css);
        BorderPane content = new BorderPane();
        content.setCenter(scene.getRoot());
        panel.setBody(content);
        scene.setRoot(panel);
        content.setPadding(new Insets(5));
        stage.setTitle("TelePorti");
        stage.setScene(scene);
        stage.setHeight(650.0);
        stage.setWidth(900.0);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}