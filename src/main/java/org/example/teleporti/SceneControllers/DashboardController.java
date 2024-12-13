package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label usernameText;

    @FXML
    protected void onLogout() {
        System.out.println("User logged out.");
    }
}
