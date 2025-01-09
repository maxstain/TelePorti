package org.example.teleporti.Utils;

import com.sun.net.httpserver.HttpServer;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.ReservationController;
import org.example.teleporti.Controllers.TrajetController;
import org.example.teleporti.Controllers.UserController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TeleportiServer {
    public static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/api/users", new UserController());
        server.createContext("/api/trajets", new TrajetController());
        server.createContext("/api/reservations", new ReservationController());
        server.createContext("/api/auth", new AuthController());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8000");
    }
}
