package org.example.teleporti.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Entities.User;
import org.kordamp.bootstrapfx.BootstrapFX;

public class UserViewController {

    private final AuthController authController = new AuthController();

    @FXML
    protected Label welcome = new Label("");
    @FXML
    protected Label nameLabel = new Label("");
    @FXML
    protected Label emailLabel = new Label("");
    @FXML
    protected Label phoneLabel = new Label("");
    @FXML
    protected Label addressLabel = new Label("");
    protected User currentUser;

    @FXML
    protected void onLogout() {
        System.out.println("User logged out.");
        try {
            authController.logout(currentUser.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/login-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWelcomeMessage(String message) {
        welcome.setText("Welcome, " + message + "!");
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        nameLabel.setText(user.getNom() + " " + user.getPrenom());
        emailLabel.setText(user.getEmail());
        phoneLabel.setText(user.getTelephone());
        addressLabel.setText(user.getAddresse());
    }

    @FXML
    public void onGoToProfile(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/profile-view.fxml"));
            Scene scene = new Scene(loader.load());
            ProfileViewController controller = loader.getController();
            controller.setCurrentUser(currentUser);
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}