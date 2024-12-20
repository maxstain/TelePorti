package org.example.teleporti.Controllers;

import org.example.teleporti.Utils.DatabaseConnection;
import org.example.teleporti.Services.ServiceUser;
import org.example.teleporti.Entities.User;

import java.sql.Connection;
import java.util.List;

public class UserController {
    private static Connection con = new DatabaseConnection().getConnection();
    private static ServiceUser _serviceUser = new ServiceUser(con);

    public boolean ajout(User user) {
        _serviceUser.ajout(user);
        return false;
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
}
