package org.example.teleporti.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.util.List;

public class UserViewController {

    private final AuthController authController = new AuthController();
    private final UserController userController = new UserController();

    @FXML
    protected Label nbrChauff;
    @FXML
    protected Label welcome = new Label("");
    @FXML
    protected Label nameLabel = new Label("");
    @FXML
    protected Label emailLabel = new Label("");
    @FXML
    protected Label phoneLabel = new Label("");
    @FXML
    protected Label addressLabel = new Label("");
    @FXML
    protected User currentUser;
    @FXML
    protected List<User> Chauffeurs;

    private int chauffeursCount = 0;

    @FXML
    public void initialize() {
        try {
            Chauffeurs = userController.getAllChauffeurs();
            chauffeursCount = Chauffeurs.size();
            nbrChauff.setText(chauffeursCount + " Chauffeurs valable(s)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onGoToChauffeurs(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.DRIVERS_VIEW));
            Scene scene = new Scene(loader.load());
            DriversViewController controller = loader.getController();
            controller.setCurrentUser(currentUser);
            controller.setWelcomeMessage(currentUser.getPrenom());
            controller.setChauffeurs(Chauffeurs);
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLogout() {
        System.out.println("User logged out.");
        try {
            authController.logout(currentUser.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.LOGIN_VIEW));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWelcomeMessage(String message) {
        welcome.setText("Welcome, " + message + "!");
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        nameLabel.setText(user.getNom() + " " + user.getPrenom());
        emailLabel.setText(user.getEmail());
        phoneLabel.setText(user.getTelephone());
        addressLabel.setText(user.getAddresse());
        setWelcomeMessage(currentUser.getNom() + " " + currentUser.getPrenom());
    }

    @FXML
    public void onGoToProfile(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.PROFILE_VIEW));
            Scene scene = new Scene(loader.load());
            ProfileViewController controller = loader.getController();
            controller.setCurrentUser(currentUser);
            controller.setWelcomeMessage(currentUser.getPrenom());
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onGoToHistorique(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Router.HISTORY_VIEW));
            Scene scene = new Scene(loader.load());
            // TODO: Implement HistoryViewController
//            HistoryViewController controller = loader.getController();
//            controller.setCurrentUser(currentUser);
//            controller.setWelcomeMessage(currentUser.getPrenom());
            Stage stage = (Stage) welcome.getScene().getWindow();
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}