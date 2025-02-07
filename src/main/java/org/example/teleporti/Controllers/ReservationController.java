package org.example.teleporti.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import javafx.collections.ObservableList;
import org.example.teleporti.Entities.Reservation;
import org.example.teleporti.Services.Reservation.ServiceReservation;
import org.example.teleporti.Utils.DatabaseConnection;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

public class ReservationController implements HttpHandler {
    private static Connection con = new DatabaseConnection().getConnection();
    private static ServiceReservation _serviceReservation = new ServiceReservation(con);
    private final Gson gson = new Gson();

    public void ajout(Reservation reservation) {
        _serviceReservation.ajout(reservation);
    }

    public void afficher() {
        _serviceReservation.afficher();
    }

    public void modifier(Reservation reservation) {
        _serviceReservation.modifier(reservation);
    }

    public void supprimer(Reservation reservation) {
        _serviceReservation.supprimer(reservation);
    }

    public int getSize() {
        return _serviceReservation.getSize();
    }

    public void truncate() {
        _serviceReservation.truncate();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";
        switch (method) {
            case "GET":
                List<Reservation> reservations = _serviceReservation.afficher();
                response = gson.toJson(reservations);
                break;
            case "POST":
                Reservation reservation = new Gson().fromJson(new String(exchange.getRequestBody().readAllBytes()), Reservation.class);
                ajout(reservation);
                response = "Reservation ajoutée";
                break;
            case "PUT":
                Reservation reservation1 = new Gson().fromJson(new String(exchange.getRequestBody().readAllBytes()), Reservation.class);
                modifier(reservation1);
                response = "Reservation modifiée";
                break;
            case "DELETE":
                Reservation reservation2 = new Gson().fromJson(new String(exchange.getRequestBody().readAllBytes()), Reservation.class);
                supprimer(reservation2);
                response = "Reservation supprimée";
                break;
            default:
                break;
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public ObservableList<Reservation> getAllReservations() {
        return _serviceReservation.getAllReservations();
    }
}
