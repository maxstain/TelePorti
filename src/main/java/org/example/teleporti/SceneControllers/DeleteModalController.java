package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;

public class DeleteModalController {

    @FXML
    public Label label;
    @FXML
    protected User user;

    private final UserController userController = new UserController();

    @FXML
    public void initialize() {
    }

    @FXML
    public void setUser(User user) {
        this.user = user;
        label.setText("Voulez-vous vraiment supprimer l'utilisateur " + user.getPrenom() + " ?");
    }

    public void onYes() {
        userController.supprimer(user);
        closeModal();
    }

    public void onNo() {
        closeModal();
    }

    private void closeModal() {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }
}
