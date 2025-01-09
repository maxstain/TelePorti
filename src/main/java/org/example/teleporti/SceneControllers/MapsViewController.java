package org.example.teleporti.SceneControllers;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.WorldMapView;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;

public class MapsViewController {

    private final AuthController authController = new AuthController();

    @FXML
    private WorldMapView worldMapView;

    @FXML
    private Label welcome = new Label("");

    protected User currentUser;

    protected ObservableList<WorldMapView.Country> countries = new ObservableListBase<>() {
        @Override
        public WorldMapView.Country get(int index) {
            return WorldMapView.Country.TN;
        }

        @Override
        public int size() {
            return 1;
        }
    };

    @FXML
    public void initialize() {
        worldMapView.getLocations().addAll(
                new WorldMapView.Location("Tunis", 36.8065, 10.1815),
                new WorldMapView.Location("Sousse", 35.8252, 10.6367),
                new WorldMapView.Location("Sfax", 34.7487, 10.7601),
                new WorldMapView.Location("Gabes", 33.8814, 10.0982)
        );

        worldMapView.setCountries(countries);

        worldMapView.setShowLocations(true);
    }

    public void setWelcomeMessage(String message) {
        welcome.setText("Welcome, " + message + "!");
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        setWelcomeMessage(currentUser.getPrenom());
    }

    @FXML
    public void onLogout() {
        try {
            authController.logout(currentUser.getId());
            Router.goToLogin(welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void onGoToUsers() {
        try {
            Router.goToUsers(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGoToSettings() {
        try {
            // Router.goToSettings(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
