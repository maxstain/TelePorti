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
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class LoginController {
    @FXML
    public Label errorText;
    @FXML
    private TextField emailText;
    @FXML
    private PasswordField passwordText;

    private final UserController userController = new UserController();

    @FXML
    protected void onFormSubmit() {
        String email = emailText.getText();
        String password = passwordText.getText();

        try {
            User user = userController.getUserByEmailAndPassword(email, password);

            if (user == null) {
                errorText.setText("Invalid email or password.");
                return;
            }

            if (email.equals(user.getEmail()) && password.equals(user.getMotDePasse())) {
                if (user.getType().equals("Admin")) {
                    errorText.setText("Login successful!");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/dashboard-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                    DashboardController controller = fxmlLoader.getController();
                    controller.setWelcomeMessage(user.getPrenom());
                    Stage stage = (Stage) emailText.getScene().getWindow();
                    stage.setScene(scene);
                } else {
                    errorText.setText("Interface inacessible pour les utilisateurs normaux.");
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/user-view.fxml"));
//                    Scene scene = new Scene(loader.load());
//                    Stage stage = (Stage) emailText.getScene().getWindow();
//                    stage.setScene(scene);
                }
            } else {
                errorText.setText("Email ou mot de passe incorrect.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void goToSignup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/signup-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) emailText.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}