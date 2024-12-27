module org.example.teleporti {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.prefs;

    opens org.example.teleporti to javafx.fxml;
    exports org.example.teleporti;
    exports org.example.teleporti.Controllers;
    opens org.example.teleporti.Controllers to javafx.fxml;
    exports org.example.teleporti.Utils;
    opens org.example.teleporti.Utils to javafx.fxml;
    exports org.example.teleporti.Services.Auth;
    opens org.example.teleporti.Services.Auth to javafx.fxml;
    exports org.example.teleporti.Services.User;
    opens org.example.teleporti.Services.User to javafx.fxml;
    exports org.example.teleporti.Services.Reservation;
    opens org.example.teleporti.Services.Reservation to javafx.fxml;
    exports org.example.teleporti.Services.Trajet;
    opens org.example.teleporti.Services.Trajet to javafx.fxml;
    exports org.example.teleporti.Entities;
    opens org.example.teleporti.Entities to javafx.fxml;
    exports org.example.teleporti.SceneControllers;
    opens org.example.teleporti.SceneControllers to javafx.fxml;
}