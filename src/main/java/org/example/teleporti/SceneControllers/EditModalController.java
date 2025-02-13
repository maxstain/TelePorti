package org.example.teleporti.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.teleporti.Controllers.ReservationController;
import org.example.teleporti.Controllers.TrajetController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.Reservation;
import org.example.teleporti.Entities.Trajet;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Constants;

public class EditModalController {

    private final UserController userController = new UserController();
    private final TrajetController trajetController = new TrajetController();
    private final ReservationController reservationController = new ReservationController();


    @FXML
    protected MenuButton trajetField = new MenuButton();
    @FXML
    protected MenuButton passagerField = new MenuButton();
    @FXML
    protected MenuButton statusField = new MenuButton();
    @FXML
    protected MenuButton conducteurs = new MenuButton();
    @FXML
    protected MenuButton pointDepart = new MenuButton();
    @FXML
    protected MenuButton destination = new MenuButton();
    @FXML
    protected DatePicker dateHeure = new DatePicker();
    @FXML
    protected TextField placesDisponibles = new TextField();
    @FXML
    protected TextField co2Economise = new TextField();
    @FXML
    protected TextField prix = new TextField();
    @FXML
    private TextField prenomField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField emailField;
    @FXML
    private MenuButton typeField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField ageField;
    @FXML
    private MenuButton governeratField;
    @FXML
    private TextField villeField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField telephoneField;

    @FXML
    private VBox userForm = new VBox();
    @FXML
    private VBox trajetForm = new VBox();
    @FXML
    private VBox reservationForm = new VBox();

    @FXML
    protected User user = null;
    @FXML
    protected User selectedConducteur = null;
    @FXML
    protected User selectedClient = null;
    @FXML
    protected User currentUser = null;

    @FXML
    protected Trajet selectedTrajet = null;
    protected Trajet trajet = null;

    @FXML
    protected Reservation selectedReservation = null;


    @FXML
    public void initialize() {
        userForm.setVisible(false);
        trajetForm.setVisible(false);
        reservationForm.setVisible(false);
    }

    @FXML
    void setReservation(Reservation selectedReservation) {
        this.selectedReservation = selectedReservation;
        if (selectedReservation != null) {
            reservationForm.setVisible(true);
            trajetField.getItems().addAll(
                    trajetController.getAllTrajets().stream().map(newTrajet -> {
                        MenuItem item = new MenuItem(newTrajet.getPointDepart() + " -> " + newTrajet.getDestination());
                        item.setOnAction(_ -> {
                            trajetField.setText(newTrajet.getPointDepart() + " -> " + newTrajet.getDestination());
                            this.trajet = newTrajet;
                        });
                        return item;
                    }).toArray(MenuItem[]::new)
            );
            passagerField.getItems().addAll(
                    userController.getAllClients().stream().map(Vuser -> {
                        MenuItem item = new MenuItem(Vuser.getPrenom() + " " + Vuser.getNom());
                        item.setOnAction(_ -> {
                            passagerField.setText(Vuser.getPrenom() + " " + Vuser.getNom());
                            selectedClient = Vuser;
                        });
                        return item;
                    }).toArray(MenuItem[]::new)
            );
            statusField.getItems().addAll(
                    Constants.statuses.stream().map(status -> {
                        MenuItem item = new MenuItem(status);
                        item.setOnAction(_ -> statusField.setText(status));
                        return item;
                    }).toArray(MenuItem[]::new)
            );
        }
        conducteurs.setText(userController.getAllChauffeurs()
                .stream()
                .filter(
                        user -> {
                            assert selectedReservation != null;
                            return user.getId() == trajetController.getTrajetById(selectedReservation.getTrajetId()).getConducteurId();
                        }
                )
                .findFirst()
                .map(User::getPrenom)
                .orElse(""));
        pointDepart.setText(trajetController.getTrajetById(selectedReservation.getTrajetId()).getPointDepart());
        destination.setText(trajetController.getTrajetById(selectedReservation.getTrajetId()).getDestination());
    }

    @FXML
    public void setCurrentUser(User user) {
        this.currentUser = user;
        if (currentUser.getType().equals("Admin")) {
            typeField.getItems().addAll(
                    Constants.roles.stream().map(role -> {
                        MenuItem item = new MenuItem(role);
                        item.setOnAction(_ -> typeField.setText(role));
                        return item;
                    }).toArray(MenuItem[]::new)
            );
        } else {
            typeField.getItems().addAll(
                    Constants.roles.stream()
                            .filter(role -> role.equals("Chauffeur") || role.equals("Client"))
                            .map(role -> {
                                MenuItem item = new MenuItem(role);
                                item.setOnAction(_ -> typeField.setText(role));
                                return item;
                            }).toArray(MenuItem[]::new)
            );
        }
    }

    @FXML
    public void setTrajet(Trajet trajet) {
        this.selectedTrajet = trajet;
        if (selectedTrajet != null) {
            trajetForm.setVisible(true);
            conducteurs.getItems().addAll(
                    userController.getAllChauffeurs().stream().map(user -> {
                        MenuItem item = new MenuItem(user.getPrenom() + " " + user.getNom());
                        item.setOnAction(_ -> {
                            conducteurs.setText(user.getPrenom() + " " + user.getNom());
                            selectedConducteur = user;
                        });
                        return item;
                    }).toArray(MenuItem[]::new)
            );
            pointDepart.getItems().addAll(
                    Constants.locations.stream().map(location -> {
                        MenuItem item = new MenuItem(location.getName());
                        item.setOnAction(_ -> pointDepart.setText(location.getName()));
                        return item;
                    }).toArray(MenuItem[]::new)
            );
            destination.getItems().addAll(
                    Constants.locations.stream().map(location -> {
                        MenuItem item = new MenuItem(location.getName());
                        item.setOnAction(_ -> destination.setText(location.getName()));
                        return item;
                    }).toArray(MenuItem[]::new)
            );
        }
        conducteurs.setText(String.valueOf(userController.getUserById(trajet.getConducteurId()).getPrenom()));
        pointDepart.setText(trajet.getPointDepart());
        destination.setText(trajet.getDestination());
        dateHeure.setValue(trajet.getDateHeure().toLocalDate());
        placesDisponibles.setText(String.valueOf(trajet.getPlacesDisponibles()));
        co2Economise.setText(String.valueOf(trajet.getCo2Economise()));
        prix.setText(String.valueOf(trajet.getPrix()));
    }

    @FXML
    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            userForm.setVisible(true);
            governeratField.getItems().addAll(
                    Constants.locations.stream().map(location -> {
                        MenuItem item = new MenuItem(location.getName());
                        item.setOnAction(_ -> governeratField.setText(location.getName()));
                        return item;
                    }).toArray(MenuItem[]::new)
            );
            typeField.getItems().addAll(
                    Constants.roles.stream().map(role -> {
                        MenuItem item = new MenuItem(role);
                        item.setOnAction(_ -> typeField.setText(role));
                        return item;
                    }).toArray(MenuItem[]::new)
            );
        }
        prenomField.setText(user.getPrenom());
        nomField.setText(user.getNom());
        emailField.setText(user.getEmail());
        typeField.setText(user.getType());
        passwordField.setText(user.getMotDePasse());
        ageField.setText(String.valueOf(user.getAge()));
        governeratField.setText(user.getGovernerat());
        villeField.setText(user.getVille());
        addressField.setText(user.getAddresse());
        telephoneField.setText(user.getTelephone());
    }

    @FXML
    public void onSave() {
        if (user != null) {
            user.setPrenom(prenomField.getText());
            user.setNom(nomField.getText());
            user.setEmail(emailField.getText());
            user.setType(typeField.getText());
            user.setMotDePasse(passwordField.getText());
            user.setAge(Integer.parseInt(ageField.getText()));
            user.setGovernerat(governeratField.getText());
            user.setVille(villeField.getText());
            user.setAddresse(addressField.getText());
            user.setTelephone(telephoneField.getText());
            userController.modifier(user);
        } else if (selectedTrajet != null) {
            selectedTrajet.setConducteurId(selectedConducteur.getId());
            selectedTrajet.setPointDepart(pointDepart.getText());
            selectedTrajet.setDestination(destination.getText());
            selectedTrajet.setDateHeure(java.sql.Date.valueOf(dateHeure.getValue()));
            selectedTrajet.setPlacesDisponibles(Integer.parseInt(placesDisponibles.getText()));
            selectedTrajet.setCo2Economise(Float.parseFloat(co2Economise.getText()));
            selectedTrajet.setPrix(Float.parseFloat(prix.getText()));
            trajetController.modifier(selectedTrajet);
        } else if (selectedReservation != null) {
            selectedReservation.setTrajetId(trajet.getId());
            selectedReservation.setPassagerId(selectedClient.getId());
            reservationController.modifier(selectedReservation);
        }
        closeModal();
    }

    @FXML
    public void onCancel() {
        closeModal();
    }

    private void closeModal() {
        Stage stage = (Stage) prenomField.getScene().getWindow();
        stage.close();
    }
}
