package org.example.teleporti.SceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;

import java.io.IOException;

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
    private TableColumn<User, String> governeratColumn;

    @FXML
    private TableColumn<User, String> telephoneColumn;

    @FXML
    private TableColumn actionsColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
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
            Router.goToLogin(welcome);
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
            Router.goToProfile(currentUser, welcome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onGotToUsers() {
        try {
            Router.goToUsers(currentUser, welcome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onGotToStats() {
        try {
            Router.goToStats(currentUser, welcome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void setCurrentUser(User user) {
        this.currentUser = user;
        setWelcomeMessage(currentUser.getNom() + " " + currentUser.getPrenom());
    }

    public void onGotToMaps() {
        try {
            Router.goToMaps(currentUser, welcome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void onGotToDashboard() {
        try {
            Router.goToDashboard(currentUser, welcome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void onGotToSettings() {
        try {
            Router.goToSettings(currentUser, welcome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void openAddModal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.ADD_MODAL_VIEW));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Add User");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openEditModal() {
        User user = usersTable.getSelectionModel().getSelectedItem();

        if (user == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.EDIT_MODAL_VIEW));
            Scene scene = new Scene(loader.load());
            EditModalController controller = loader.getController();
            controller.setUser(user);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Edit User");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openDeleteModal() {
        User user = usersTable.getSelectionModel().getSelectedItem();

        if (user == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.DELETE_MODAL_VIEW));
            Scene scene = new Scene(loader.load());
            DeleteModalController controller = loader.getController();
            controller.setUser(user);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Delete User");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
