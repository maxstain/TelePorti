package org.example.teleporti.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Services.User.ServiceUser;
import org.example.teleporti.Utils.DatabaseConnection;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

public class UserController implements HttpHandler {
    private static Connection con = new DatabaseConnection().getConnection();
    private static ServiceUser _serviceUser = new ServiceUser(con);
    private final Gson gson = new Gson();

    public boolean ajout(User user) {
        return _serviceUser.ajout(user);
    }

    public void afficher() {
        _serviceUser.afficher();
    }

    public List<User> afficherList() {
        return _serviceUser.afficherList();
    }

    public void modifier(User user) {
        _serviceUser.modifier(user);
    }

    public void supprimer(User user) {
        _serviceUser.supprimer(user);
    }

    public int getSize() {
        return _serviceUser.getSize();
    }

    public void truncate() {
        _serviceUser.truncate();
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return _serviceUser.getUserByEmailAndPassword(email, password);
    }

    public List<User> rechercher(String search) {
        return _serviceUser.rechercher(search);
    }

    public int countByType(String type) {
        return _serviceUser.countByType(type);
    }

    public int countByGovernerat(String governerat) {
        return _serviceUser.countByGovernerat(governerat);
    }

    public List<User> getAllChauffeurs() {
        return _serviceUser.getAllChauffeurs();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";

        if ("GET".equals(method)) {
            response = gson.toJson(afficherList());
        } else if ("POST".equals(method)) {
            User user = gson.fromJson(new String(exchange.getRequestBody().readAllBytes()), User.class);
            ajout(user);
            response = gson.toJson(afficherList());
        } else if ("PUT".equals(method)) {
            User user = gson.fromJson(new String(exchange.getRequestBody().readAllBytes()), User.class);
            modifier(user);
            response = gson.toJson(afficherList());
        } else if ("DELETE".equals(method)) {
            User user = gson.fromJson(new String(exchange.getRequestBody().readAllBytes()), User.class);
            supprimer(user);
            response = gson.toJson(afficherList());
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
