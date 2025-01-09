package org.example.teleporti.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.teleporti.Entities.User;
import org.example.teleporti.SceneControllers.LoginViewController;
import org.example.teleporti.Services.Auth.ServiceAuth;
import org.example.teleporti.Utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.prefs.Preferences;

public class AuthController implements HttpHandler {
    private static final Connection con = new DatabaseConnection().getConnection();
    private static final ServiceAuth _serviceAuth = new ServiceAuth(con);
    private final Gson gson = new Gson();

    public boolean connection(String email, String password, boolean staySignedIn) {
        User user = _serviceAuth.getUserByEmailAndPassword(email, password);
        if (user != null) {
            if (staySignedIn) {
                saveSessionToken(user.getId());
                Preferences prefs = Preferences.userNodeForPackage(LoginViewController.class);
                prefs.put("sessionToken", getSessionToken(user.getId()));
            }
            return true;
        }
        return false;
    }

    public void saveSessionToken(int userId) {
        _serviceAuth.saveSessionToken(userId);
    }

    public boolean validateSession(String sessionToken) {
        return _serviceAuth.validateSession(sessionToken);
    }

    public boolean inscription(User newUser) {
        return _serviceAuth.inscription(newUser);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return _serviceAuth.getUserByEmailAndPassword(email, password);
    }

    public void logout(int userId) {
        _serviceAuth.logout(userId);
    }

    public String getSessionToken(int userId) {
        return _serviceAuth.getSessionToken(userId);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        if (method.equals("POST")) {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/auth/login")) {
                String body = new String(exchange.getRequestBody().readAllBytes());
                User user = gson.fromJson(body, User.class);
                boolean staySignedIn = Boolean.parseBoolean(exchange.getRequestHeaders().getFirst("staySignedIn"));
                if (connection(user.getEmail(), user.getMotDePasse(), staySignedIn)) {
                    exchange.sendResponseHeaders(200, 0);
                } else {
                    exchange.sendResponseHeaders(401, 0);
                }
            } else if (path.equals("/auth/logout")) {
                String sessionToken = exchange.getRequestHeaders().getFirst("sessionToken");
                if (validateSession(sessionToken)) {
                    logout(_serviceAuth.getUserIdBySessionToken(sessionToken));
                    exchange.sendResponseHeaders(200, 0);
                } else {
                    exchange.sendResponseHeaders(401, 0);
                }
            } else if (path.equals("/auth/inscription")) {
                String body = new String(exchange.getRequestBody().readAllBytes());
                User user = gson.fromJson(body, User.class);
                if (inscription(user)) {
                    exchange.sendResponseHeaders(200, 0);
                } else {
                    exchange.sendResponseHeaders(401, 0);
                }
            }
        } else {
            exchange.sendResponseHeaders(405, 0);
        }
        exchange.close();
    }
}