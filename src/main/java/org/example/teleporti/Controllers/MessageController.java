package org.example.teleporti.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.teleporti.Entities.Message;
import org.example.teleporti.Services.Message.ServiceMessage;
import org.example.teleporti.Utils.DatabaseConnection;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

public class MessageController implements HttpHandler {
    private static Connection con = new DatabaseConnection().getConnection();
    private static ServiceMessage _serviceMessage = new ServiceMessage(con);
    private final Gson gson = new Gson();

    public boolean ajout(Message message) {
        return _serviceMessage.ajout(message);
    }

    public void afficher() {
        _serviceMessage.afficher();
    }

    public List<Message> afficherList() {
        return _serviceMessage.afficher();
    }

    public void modifier(Message message) {
        _serviceMessage.modifier(message);
    }

    public void supprimer(Message message) {
        _serviceMessage.supprimer(message);
    }

    public int getSize() {
        return _serviceMessage.getSize();
    }

    public void truncate() {
        _serviceMessage.truncate();
    }

    public List<Message> rechercher(String search) {
        return _serviceMessage.rechercher(search);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";

        if ("GET".equals(method)) {
            response = gson.toJson(afficherList());
        } else if ("POST".equals(method)) {
            Message message = gson.fromJson(new String(exchange.getRequestBody().readAllBytes()), Message.class);
            ajout(message);
            response = gson.toJson(afficherList());
        } else if ("PUT".equals(method)) {
            Message message = gson.fromJson(new String(exchange.getRequestBody().readAllBytes()), Message.class);
            modifier(message);
            response = gson.toJson(afficherList());
        } else if ("DELETE".equals(method)) {
            Message message = gson.fromJson(new String(exchange.getRequestBody().readAllBytes()), Message.class);
            supprimer(message);
            response = gson.toJson(afficherList());
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public List<Message> getAllCurrentUserConversations(int currentUserId) {
        return _serviceMessage.getAllCurrentUserConversations(currentUserId);
    }

    public void createMessagesTable() {
        _serviceMessage.createMessagesTable();
    }
}
