package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.teleporti.Entities.User;

public class ProfileViewController {
    protected Label nameLabel;
    protected Label emailLabel;
    protected User currentUser;

    @FXML
    public void initialize() {
        System.out.println("Profile view initialized.");
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        nameLabel.setText(currentUser.getPrenom() + " " + currentUser.getNom());
        emailLabel.setText(currentUser.getEmail());
    }
}
