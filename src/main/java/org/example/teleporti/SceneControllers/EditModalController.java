package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;

public class EditModalController {

    private final UserController userController = new UserController();

    @FXML
    private TextField prenomField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField typeField;
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
    }

    @FXML
    public void setUser(User user) {
        this.user = user;
        prenomField.setText(user.getPrenom());
        nomField.setText(user.getNom());
        emailField.setText(user.getEmail());
        typeField.setText(user.getType());
        passwordField.setText(user.getMotDePasse());
        ageField.setText(String.valueOf(user.getAge()));
        governeratField.setText(user.getGovernerat());
        villeField.setText(user.getVille());
        telephoneField.setText(user.getTelephone());
    }

    @FXML
    public void onSave() {
        user.setPrenom(prenomField.getText());
        user.setNom(nomField.getText());
        user.setEmail(emailField.getText());
        user.setType(typeField.getText());
        user.setMotDePasse(passwordField.getText());
        user.setAge(Integer.parseInt(ageField.getText()));
        user.setGovernerat(governeratField.getText());
        user.setVille(villeField.getText());
        user.setTelephone(telephoneField.getText());
        userController.modifier(user);
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
