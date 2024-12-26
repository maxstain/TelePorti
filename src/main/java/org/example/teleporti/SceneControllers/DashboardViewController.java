package org.example.teleporti.SceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

import java.util.Objects;

public class DashboardViewController {

    private final UserController userController = new UserController();
    private final AuthController authController = new AuthController();
    @FXML
    protected Label welcome;
    @FXML
    protected TextField searchField;
    @FXML
    protected PieChart usersPieChart;
    @FXML
    private User currentUser;

    @FXML
    public void initialize() {
        usersPieChart.setData(FXCollections.observableArrayList(
                new PieChart.Data("Admins", userController.countByType("Admin")),
                new PieChart.Data("Clients", userController.countByType("Client")),
                new PieChart.Data("Chauffeurs", userController.countByType("Chauffeur"))
        ));
    }

    @FXML
    protected void onLogout() {
        System.out.println("User logged out.");
        try {
            authController.logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/login-view.fxml"));
            Scene scene = new Scene(loader.load());
            String css = Objects.requireNonNull(getClass().getResource("/org/example/teleporti/Styles/Auth.css")).toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = (Stage) welcome.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setWelcomeMessage(String string) {
        welcome.setText("Welcome, " + string + "!");
    }

    public void onGotToProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/profile-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcome.getScene().getWindow();
            ProfileViewController controller = loader.getController();
            controller.setCurrentUser(currentUser);
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
            UsersViewController controller = loader.getController();
            controller.setCurrentUser(currentUser);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onGotToStats() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/teleporti/Views/stats-view.fxml"));
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

    @FXML
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

    @FXML
    public void onGotToDashboard(ActionEvent actionEvent) {
        // To be implemented
    }

    @FXML
    public void onGotToSettings(ActionEvent actionEvent) {
        // To be implemented
    }
}
