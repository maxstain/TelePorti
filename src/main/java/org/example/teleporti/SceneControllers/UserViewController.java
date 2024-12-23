package org.example.teleporti.SceneControllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.AuthController;
import org.kordamp.bootstrapfx.BootstrapFX;

public class UserViewController {

    private final AuthController authController = new AuthController();
    public Label welcome;

    @FXML
    protected void onLogout() {
        System.out.println("User logged out.");
        try {
            authController.logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/login-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setWelcomeMessage(String string) {
        welcome.setText("Welcome, " + string + "!");
    }
}
