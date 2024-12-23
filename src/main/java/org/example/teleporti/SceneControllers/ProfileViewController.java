package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileViewController {
    public Label nameLabel;
    public Label emailLabel;

    @FXML
    public void initialize() {
        System.out.println("Profile view initialized.");
    }
}
