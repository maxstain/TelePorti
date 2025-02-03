package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.teleporti.Controllers.ReservationController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;

public class ReservationsViewController {

    private final UserController userController = new UserController();
    private final ReservationController reservationController = new ReservationController();

    protected User currentUser;

    @FXML
    protected Label welcome;

    @FXML
    private User selectedUser;

    @FXML
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        setWelcomeMessage(currentUser.getPrenom());
    }

    @FXML
    public void setWelcomeMessage(String message) {
        welcome.setText("Bienvenue, " + message + "!");
    }
}
