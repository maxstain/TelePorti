package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.teleporti.Entities.User;

public class SettingsViewController {

    @FXML
    protected Label welcome = new Label("");

    protected User currentUser;

    @FXML
    private void initialize() {
    }

    @FXML
    public void setCurrentUser(User user) {
        this.currentUser = user;
        this.welcome.setText("Bienvenue " + user.getPrenom());
    }
}
