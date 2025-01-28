package org.example.teleporti.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;

public class MessagesViewController {

    private final AuthController authController = new AuthController();
    private final UserController _userController = new UserController();

    @FXML
    private Label welcome = new Label("");

    protected User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        welcome.setText("Welcome " + currentUser.getPrenom() + "!");
    }

    @FXML
    public void onGoToProfile() {
        try {
            Router.goToProfile(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onGoToMessages() {
        try {
            Router.goToMessages(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}
