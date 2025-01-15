package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;

public class AddModalController {

    private final UserController userController = new UserController();

    @FXML
    private TextField prenomField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField governeratField;
    @FXML
    private TextField villeField;
    @FXML
    private TextField telephoneField;

    @FXML
    protected User user;

    @FXML
    public void initialize() {
        prenomField.setText("");
        nomField.setText("");
        emailField.setText("");
        passwordField.setText("");
        ageField.setText("");
        governeratField.setText("");
        villeField.setText("");
        telephoneField.setText("");
    }

    @FXML
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void onSave() {
        user = User.empty();
        user.setPrenom(prenomField.getText());
        user.setNom(nomField.getText());
        user.setEmail(emailField.getText());
        user.setMotDePasse(passwordField.getText());
        user.setAge(Integer.parseInt(ageField.getText()));
        user.setGovernerat(governeratField.getText());
        user.setVille(villeField.getText());
        user.setTelephone(telephoneField.getText());
        userController.ajout(user);
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
