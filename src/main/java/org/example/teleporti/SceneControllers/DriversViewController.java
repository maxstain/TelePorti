package org.example.teleporti.SceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.util.List;

public class DriversViewController {

    private final UserController userController = new UserController();
    private final AuthController authController = new AuthController();
    @FXML
    protected Label welcome;
    @FXML
    protected TextField searchField;
    @FXML
    protected User currentUser;

    @FXML
    protected TableView<User> usersTable;

    @FXML
    protected TableColumn<User, Integer> idColumn;

    @FXML
    protected TableColumn<User, String> emailColumn;

    @FXML
    protected TableColumn<User, String> nomColumn;

    @FXML
    protected TableColumn<User, String> prenomColumn;

    @FXML
    protected TableColumn<User, String> governeratColumn;

    @FXML
    protected TableColumn<User, String> telephoneColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        governeratColumn.setCellValueFactory(new PropertyValueFactory<>("governerat"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<User> users = FXCollections.observableArrayList(userController.rechercher(newValue));
            usersTable.setItems(users);
        });
        ObservableList<User> users = FXCollections.observableArrayList(userController.afficherList());
        usersTable.setItems(users);
    }

    @FXML
    protected void onLogout() {
        System.out.println("User logged out.");
        try {
            authController.logout(currentUser.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.LOGIN_VIEW));
            Scene scene = new Scene(loader.load());
            String css = getClass().getResource("/org/example/teleporti/Styles/Auth.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = (Stage) welcome.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onRefresh() {
        ObservableList<User> users = FXCollections.observableArrayList(userController.afficherList());
        usersTable.setItems(users);
        System.out.println("Refreshed.");
    }

    public void setWelcomeMessage(String string) {
        welcome.setText("Welcome, " + string + "!");
    }

    public void onGotToProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.PROFILE_VIEW));
            Scene scene = new Scene(loader.load());
            ProfileViewController controller = loader.getController();
            controller.setCurrentUser(currentUser);
            controller.setWelcomeMessage(currentUser.getNom() + " " + currentUser.getPrenom());
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void setCurrentUser(User user) {
        this.currentUser = user;
        setWelcomeMessage(currentUser.getNom() + " " + currentUser.getPrenom());
    }

    @FXML
    public void setChauffeurs(List<User> chauffeurs) {
        ObservableList<User> users = FXCollections.observableArrayList(chauffeurs);
        usersTable.setItems(users);
    }
}
