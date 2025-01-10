package org.example.teleporti.SceneControllers;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.controlsfx.control.WorldMapView;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Constants;
import org.example.teleporti.Utils.Router;

public class MapsViewController {

    private final AuthController authController = new AuthController();
    private final UserController userController = new UserController();

    @FXML
    private WorldMapView worldMapView;

    @FXML
    private Label welcome = new Label("");

    protected User currentUser;

    private final ObservableList<WorldMapView.Location> locations = new ObservableListBase<>() {
        @Override
        public WorldMapView.Location get(int index) {
            return Constants.locations.stream().filter(location -> location.getName().equals(userController.getAllGovernerats().get(index))).findFirst().orElse(null);
        }

        @Override
        public int size() {
            return userController.getAllGovernerats().size();
        }
    };

    private final ObservableList<WorldMapView.Country> countries = new ObservableListBase<>() {
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
        worldMapView.getLocations().addAll(locations);
        worldMapView.locationViewFactoryProperty().set(
                (location) -> {
                    Circle circle = new Circle(1.5);
                    circle.setFill(Color.RED);
                    return circle;
                }
        );
        worldMapView.setShowLocations(true);
        worldMapView.setCountries(countries);
        worldMapView.countryViewFactoryProperty().set(
                (country) -> {
                    WorldMapView.CountryView countryView = new WorldMapView.CountryView(country);
                    countryView.setOpacity(0.5);
                    return countryView;
                }
        );
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
