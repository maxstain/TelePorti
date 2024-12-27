package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.teleporti.Entities.User;

public class ProfileViewController {
    @FXML
    protected Label nameLabel = new Label("");
    @FXML
    protected Label emailLabel = new Label("");
    protected User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        nameLabel.setText(currentUser.getPrenom() + " " + currentUser.getNom());
        emailLabel.setText(currentUser.getEmail());
    }
}
