package org.example.teleporti.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;

import java.io.IOException;

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
            Router.goToLogin(welcome);
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
        setWelcomeMessage(currentUser.getPrenom());
    }

    @FXML
    public void onGoToHome() {
        try {
            if (currentUser.getType().equals("Admin")) {
                Router.goToDashboard(currentUser, welcome);
            } else {
                Router.goToUser(currentUser, welcome);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openEditModal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.EDIT_MODAL_VIEW));
            Scene scene = new Scene(loader.load());
            EditModalController controller = loader.getController();
            controller.setUser(currentUser);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Edit User");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
