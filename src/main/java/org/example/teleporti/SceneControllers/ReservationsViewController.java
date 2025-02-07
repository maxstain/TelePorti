package org.example.teleporti.SceneControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.ReservationController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.Reservation;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;

import java.io.IOException;

public class ReservationsViewController {

    private final AuthController authController = new AuthController();
    private final UserController userController = new UserController();
    private final ReservationController reservationController = new ReservationController();

    @FXML
    public TableColumn<Reservation, Integer> idColumn;
    @FXML
    public TableColumn<Reservation, String> trajetIdColumn;
    @FXML
    public TableColumn<Reservation, String> passagerIdColumn;
    @FXML
    public TableColumn<Reservation, String> statusColumn;

    protected User currentUser;

    @FXML
    protected Label welcome;

    @FXML
    private User selectedUser;

    @FXML
    private TableView<Reservation> reservationsTable = new TableView<>();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        trajetIdColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("trajetId"));
        passagerIdColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("passagerId"));
        statusColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));
        ObservableList<Reservation> reservations = reservationController.getAllReservations();
        reservationsTable.setItems(reservations);
    }

    @FXML
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        setWelcomeMessage(currentUser.getPrenom());
    }

    @FXML
    public void setWelcomeMessage(String message) {
        welcome.setText("Bienvenue, " + message + "!");
    }

    public void onGoToStats() {
        try {
            Router.goToStats(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGoToDashboard() {
        try {
            Router.goToDashboard(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGoToProfile() {
        try {
            Router.goToProfile(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGoToMaps() {
        try {
            Router.goToMaps(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGoToMessages() {
        try {
            Router.goToMessages(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGoToUsers() {
        try {
            Router.goToUsers(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGoToTrajets() {
        try {
            Router.goToTrajets(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGoToReservations() {
        try {
            Router.goToReservations(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLogout() {
        try {
            authController.logout(currentUser.getId());
            Router.goToLogin(welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRefresh() {
        try {
            Router.goToReservations(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
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
        Reservation reservation = reservationsTable.getSelectionModel().getSelectedItem();

        if (reservation == null) {
            Router.openErrorMessageModal("Merci de selectionner un utilisateur à modifier.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.EDIT_MODAL_VIEW));
            Scene scene = new Scene(loader.load());
            EditModalController controller = loader.getController();
            controller.setReservation(reservation);
            controller.setCurrentUser(currentUser);
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
        Reservation reservation = reservationsTable.getSelectionModel().getSelectedItem();

        if (reservation == null) {
            Router.openErrorMessageModal("Merci de selectionner un utilisateur à supprimer.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.DELETE_MODAL_VIEW));
            Scene scene = new Scene(loader.load());
            DeleteModalController controller = loader.getController();
            controller.setReservation(reservation);
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
