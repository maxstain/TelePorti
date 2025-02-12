package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.ReservationController;
import org.example.teleporti.Controllers.TrajetController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.Reservation;
import org.example.teleporti.Entities.Trajet;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Constants;

public class AddModalController {

    private final UserController userController = new UserController();
    private final ReservationController reservationController = new ReservationController();
    private final TrajetController trajetController = new TrajetController();

    @FXML
    protected VBox addUserForm = new VBox();
    @FXML
    protected VBox addTrajetForm = new VBox();
    @FXML
    protected VBox addReservationForm = new VBox();

    @FXML
    private TextField prenomField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField ageField;
    @FXML
    private MenuButton governeratField;
    @FXML
    private TextField villeField;
    @FXML
    private TextField telephoneField;

    @FXML
    private User user = null;
    @FXML
    private Trajet trajet = null;
    @FXML
    private Reservation reservation = null;

    private boolean isUserForm = false;
    private boolean isTrajetForm = false;
    private boolean isReservationForm = false;

    @FXML
    public void initialize() {
        user = User.empty();
        trajet = Trajet.empty();
        reservation = Reservation.empty();
        addUserForm.setVisible(false);
        addTrajetForm.setVisible(false);
        addReservationForm.setVisible(false);
    }

    @FXML
    public void setUser() {
        isUserForm = true;
        isTrajetForm = false;
        isReservationForm = false;
        addUserForm.setVisible(true);
        prenomField.setText("");
        nomField.setText("");
        emailField.setText("");
        passwordField.setText("");
        ageField.setText("");
        governeratField.getItems().addAll(
                Constants.locations.stream().map(location -> {
                    MenuItem item = new MenuItem(location.getName());
                    item.setOnAction(_ -> governeratField.setText(location.getName()));
                    return item;
                }).toArray(MenuItem[]::new)
        );
        villeField.setText("");
        telephoneField.setText("");
    }

    @FXML
    public void setTrajet() {
        isTrajetForm = true;
        isReservationForm = false;
        isUserForm = false;
        addUserForm.setVisible(true);
    }

    public void setReservation() {
        isReservationForm = true;
        isTrajetForm = false;
        isUserForm = false;
        addUserForm.setVisible(true);
    }

    @FXML
    public void onSave() {
        if (isUserForm) {
            user.setPrenom(prenomField.getText());
            user.setNom(nomField.getText());
            user.setEmail(emailField.getText());
            user.setMotDePasse(passwordField.getText());
            user.setAge(Integer.parseInt(ageField.getText()));
            user.setGovernerat(governeratField.getText());
            user.setVille(villeField.getText());
            user.setTelephone(telephoneField.getText());
            userController.ajout(user);
        } else if (isTrajetForm) {
            // TODO: Implement trajet form
        } else if (isReservationForm) {
            // TODO: Implement reservation form
        }
        closeModal();
    }

    @FXML
    public void onCancel() {
        closeModal();
    }

    private void closeModal() {
        Stage stage = (Stage) prenomField.getScene().getWindow();
        stage.close();
    }
}
