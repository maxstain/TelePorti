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

    opens org.example.teleporti to javafx.fxml;
    exports org.example.teleporti;
    exports org.example.teleporti.Controllers;
    opens org.example.teleporti.Controllers to javafx.fxml;
    exports org.example.teleporti.Utils;
    opens org.example.teleporti.Utils to javafx.fxml;
    exports org.example.teleporti.Services;
    opens org.example.teleporti.Services to javafx.fxml;
    exports org.example.teleporti.Entities;
    opens org.example.teleporti.Entities to javafx.fxml;
}