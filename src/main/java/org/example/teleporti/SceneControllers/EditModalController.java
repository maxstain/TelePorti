package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Constants;

public class EditModalController {

    private final UserController userController = new UserController();


    @FXML
    private TextField prenomField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField emailField;
    @FXML
    private MenuButton typeField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField ageField;
    @FXML
    private MenuButton governeratField;
    @FXML
    private TextField villeField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField telephoneField;
    @FXML
    protected User user;

    protected User currentUser;

    @FXML
    public void initialize() {
        governeratField.getItems().addAll(
                Constants.locations.stream().map(location -> {
                    MenuItem item = new MenuItem(location.getName());
                    item.setOnAction(_ -> governeratField.setText(location.getName()));
                    return item;
                }).toArray(MenuItem[]::new)
        );
    }

    @FXML
    public void setCurrentUser(User user) {
        this.currentUser = user;
        if (currentUser.getType().equals("Admin")) {
            typeField.getItems().addAll(
                    Constants.roles.stream().map(role -> {
                        MenuItem item = new MenuItem(role);
                        item.setOnAction(_ -> typeField.setText(role));
                        return item;
                    }).toArray(MenuItem[]::new)
            );
        } else {
            typeField.getItems().addAll(
                    Constants.roles.stream()
                            .filter(role -> role.equals("Chauffeur") || role.equals("Client"))
                            .map(role -> {
                                MenuItem item = new MenuItem(role);
                                item.setOnAction(_ -> typeField.setText(role));
                                return item;
                            }).toArray(MenuItem[]::new)
            );
        }
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
        addressField.setText(user.getAddresse());
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
        user.setAddresse(addressField.getText());
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
