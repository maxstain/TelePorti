package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    public Label errorText;
    @FXML
    private TextField emailText;
    @FXML
    private PasswordField passwordText;

    @FXML
    protected void onFormSubmit() {
        String email = emailText.getText();
        String password = passwordText.getText();

        // Add your login logic here
        if (email.equals("user@example.com") && password.equals("password")) {
            errorText.setText("Login successful!");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/teleporti/dashboard-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) emailText.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            errorText.setText("Invalid email or password.");
        }
    }
}