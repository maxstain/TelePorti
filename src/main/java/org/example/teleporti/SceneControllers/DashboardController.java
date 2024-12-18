package org.example.teleporti.SceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;

public class DashboardController {

    private UserController userController = new UserController();

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> typeColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        ObservableList<User> users = FXCollections.observableArrayList(userController.afficherList());
        usersTable.setItems(users);
    }

    @FXML
    protected void onLogout() {
        System.out.println("User logged out.");
    }

    @FXML
    protected void onRefresh() {
        ObservableList<User> users = FXCollections.observableArrayList(userController.afficherList());
        usersTable.setItems(users);
        System.out.println("Refreshed.");
    }
}
