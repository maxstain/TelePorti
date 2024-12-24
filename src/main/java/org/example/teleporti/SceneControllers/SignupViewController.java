package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.EmailVerification;

import java.io.IOException;

public class SignupViewController {

    private final UserController userController = new UserController();

    @FXML
    public Label errorText;
    @FXML
    public TextField ageText;
    @FXML
    public TextField emailText;
    @FXML
    public PasswordField passwordText;
    @FXML
    public TextField nomText;
    @FXML
    public TextField prenomText;

    @FXML
    protected void onFormSubmit() {
        String nom = nomText.getText();
        String prenom = prenomText.getText();
        String email = emailText.getText();
        String password = passwordText.getText();
        int age = Integer.parseInt(ageText.getText());

        User newUser = new User(userController.getSize() + 1, nom, prenom, age, email, password, "Client");

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty() || ageText.getText().isEmpty()) {
            errorText.setText("Please fill in all fields.");
            return;
        } else if (EmailVerification.isValid(email)) {
            errorText.setText("Please enter a valid email address.");
            return;
        }

        if (userController.ajout(newUser)) {
            errorText.setText("Registration successful!");
            goToLogin();
        } else {
            errorText.setText("Registration failed. Please try again.");
        }
    }

    @FXML
    protected void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/login-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) emailText.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}