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
import org.kordamp.bootstrapfx.BootstrapFX;

public class UsersViewController {

    private final UserController userController = new UserController();
    private final AuthController authController = new AuthController();
    @FXML
    protected Label welcome;
    @FXML
    protected TextField searchField;
    @FXML
    protected User currentUser;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    public TableColumn<User, String> nomColumn;

    @FXML
    public TableColumn<User, String> prenomColumn;

    @FXML
    private TableColumn<User, String> typeColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
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
            authController.logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/login-view.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/profile-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onGotToUsers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/users-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onGotToStats() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/dashboard-view.fxml"));
            Scene scene = new Scene(loader.load());
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
    }

    public void onGotToMaps(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/maps-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
