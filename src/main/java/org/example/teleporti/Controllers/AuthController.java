package org.example.teleporti.Controllers;

import org.example.teleporti.Entities.User;
import org.example.teleporti.Services.Auth.ServiceAuth;
import org.example.teleporti.Utils.DatabaseConnection;

import java.sql.Connection;

public class AuthController {
    private final UserController userController = new UserController();
    private static Connection con = new DatabaseConnection().getConnection();
    private static ServiceAuth _serviceAuth = new ServiceAuth(con);

    public boolean connection(String email, String password) {
        return userController.getUserByEmailAndPassword(email, password) != null;
    }

    public boolean inscription(String nom, String prenom, int age, String email, String password, String type) {
        return userController.ajout(new User(userController.getSize() + 1, nom, prenom, age, email, password, type));
    }

    public boolean logout() {
        return _serviceAuth.logout();
    }
}
