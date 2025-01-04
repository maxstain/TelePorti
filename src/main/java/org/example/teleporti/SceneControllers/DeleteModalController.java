package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import org.example.teleporti.Entities.User;

public class DeleteModalController {
    @FXML
    protected User user;

    @FXML
    public void initialize() {
    }

    @FXML
    public void setUser(User user) {
        this.user = user;
    }
}
