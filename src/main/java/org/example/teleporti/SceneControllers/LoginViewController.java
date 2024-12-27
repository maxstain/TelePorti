package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Entities.User;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class LoginViewController {
    @FXML
    protected Label errorText;
    @FXML
    protected TextField emailText;
    @FXML
    protected PasswordField passwordText;
    @FXML
    protected CheckBox staySignedInCheckBox;

    protected final AuthController authController = new AuthController();

    @FXML
    protected void onFormSubmit() {
        String email = emailText.getText();
        String password = passwordText.getText();
        Scene scene;

        try {
            User user = authController.getUserByEmailAndPassword(email, password);

            if (!authController.connection(email, password, staySignedInCheckBox.isSelected())) {
                errorText.setText("Email ou mot de passe incorrect.");
            } else {
                errorText.setText("Login successful!");
                FXMLLoader fxmlLoader;
                if (user.getType().equals("Admin")) {
                    fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/dashboard-view.fxml"));
                    scene = new Scene(fxmlLoader.load());
                    scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                    DashboardViewController controller = fxmlLoader.getController();
                    controller.setWelcomeMessage(user.getPrenom());
                    controller.setCurrentUser(user);
                    Stage stage = (Stage) emailText.getScene().getWindow();
                    stage.setScene(scene);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/user-view.fxml"));
                    scene = new Scene(loader.load());
                    UserViewController controller = loader.getController();
                    controller.setCurrentUser(user);
                    controller.setWelcomeMessage(user.getPrenom());
                    Stage stage = (Stage) emailText.getScene().getWindow();
                    scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                    stage.setScene(scene);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}