package org.example.teleporti.SceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.util.Objects;

public class DashboardViewController {

    private final UserController userController = new UserController();
    private final AuthController authController = new AuthController();
    @FXML
    protected Label welcome = new Label("");
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
            authController.logout(currentUser.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.LOGIN_VIEW));
            Scene scene = new Scene(loader.load());
            String css = Objects.requireNonNull(getClass().getResource("/org/example/teleporti/Styles/Auth.css")).toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = (Stage) welcome.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void setWelcomeMessage(String string) {
        welcome.setText("Welcome, " + string + "!");
    }

    public void onGotToProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.PROFILE_VIEW));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcome.getScene().getWindow();
            ProfileViewController controller = loader.getController();
            controller.setCurrentUser(currentUser);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void onGotToUsers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.USERS_VIEW));
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
            Router.goToStats(currentUser, welcome);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @FXML
    public void setCurrentUser(User user) {
        this.currentUser = user;
        setWelcomeMessage(currentUser.getNom() + " " + currentUser.getPrenom());
    }

    @FXML
    public void onGotToMaps() {
        try {
            Router.goToMaps(currentUser, welcome);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @FXML
    public void onGotToDashboard() {
        try {
            Router.goToDashboard(currentUser, welcome);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @FXML
    public void onGotToSettings() {
        // TODO: To be implemented
    }
}
