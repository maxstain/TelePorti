package org.example.teleporti.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.teleporti.Entities.Trajet;
import org.example.teleporti.Services.Trajet.ServiceTrajet;
import org.example.teleporti.Utils.DatabaseConnection;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

public class TrajetController implements HttpHandler {
    private static Connection con = new DatabaseConnection().getConnection();
    private static ServiceTrajet _serviceTrajet = new ServiceTrajet(con);
    private final Gson gson = new Gson();

    public void ajout(Trajet trajet) {
        _serviceTrajet.ajout(trajet);
    }

    public void afficher() {
        _serviceTrajet.afficher();
    }

    public void modifier(Trajet trajet) {
        _serviceTrajet.modifier(trajet);
    }

    public void supprimer(Trajet trajet) {
        _serviceTrajet.supprimer(trajet);
    }

    public int getSize() {
        return _serviceTrajet.getSize();
    }

    public void truncate() {
        _serviceTrajet.truncate();
    }

    public List<Trajet> getTrajetsByUserId(int id) {
        return _serviceTrajet.getTrajetsByUserId(id);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";

        if (method.equals("GET")) {
            response = gson.toJson(_serviceTrajet.afficher());
        } else if (method.equals("POST")) {
            String body = new String(exchange.getRequestBody().readAllBytes());
            Trajet trajet = gson.fromJson(body, Trajet.class);
            ajout(trajet);
            response = gson.toJson(trajet);
        } else if (method.equals("PUT")) {
            String body = new String(exchange.getRequestBody().readAllBytes());
            Trajet trajet = gson.fromJson(body, Trajet.class);
            modifier(trajet);
            response = gson.toJson(trajet);
        } else if (method.equals("DELETE")) {
            String body = new String(exchange.getRequestBody().readAllBytes());
            Trajet trajet = gson.fromJson(body, Trajet.class);
            supprimer(trajet);
            response = gson.toJson(trajet);
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public Double countAvgCO2EmissionByGovernerat(String governerat) {
        return _serviceTrajet.countAvgCO2EmissionByGovernerat(governerat);
    }

    public Double countAvgRideCostByGovernerat(String governerat) {
        return _serviceTrajet.countAvgRideCostByGovernerat(governerat);
    }

    public List<Trajet> getAllTrajets() {
        return _serviceTrajet.getAllTrajets();
    }
}
