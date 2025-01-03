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

public class ProfileViewController {
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
    @FXML
    protected Label villeLabel = new Label("");
    @FXML
    protected Label paysLabel = new Label("");
    @FXML
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
        villeLabel.setText(user.getVille());
        paysLabel.setText(user.getGovernerat());
    }

    @FXML
    public void onEditProfile(ActionEvent actionEvent) {
    }

    @FXML
    public void onGoToHome(ActionEvent actionEvent) {
        FXMLLoader loader;
        try {
            if (currentUser.getType().equals("Admin")) {
                loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/dashboard-view.fxml"));
                Scene scene = new Scene(loader.load());
                DashboardViewController controller = loader.getController();
                controller.setCurrentUser(currentUser);
                Stage stage = (Stage) welcome.getScene().getWindow();
                scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                stage.setScene(scene);
            } else {
                loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/user-view.fxml"));
                Scene scene = new Scene(loader.load());
                UserViewController controller = loader.getController();
                controller.setCurrentUser(currentUser);
                Stage stage = (Stage) welcome.getScene().getWindow();
                scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                stage.setScene(scene);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
