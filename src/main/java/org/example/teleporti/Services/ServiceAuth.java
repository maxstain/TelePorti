package org.example.teleporti.Services;

import org.example.teleporti.Entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceAuth implements IServiceAuth {

    private Statement ste; // Objet Statement utilisé pour exécuter les requêtes SQL
    int size = 0; // Variable pour stocker le nombre d'utilisateurs

    // Le constructeur initialise l'objet Statement en utilisant la connexion de base de données fournie.
    public ServiceAuth(Connection con) {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param email
     * @param password
     * @return User
     */
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return null;
    }

    /**
     * @param email
     * @param password
     * @return
     */
    @Override
    public boolean connection(String email, String password) {
        return false;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean inscription(User user) {
        return false;
    }

    /**
     *
     */
    @Override
    public boolean logout() {
        return true;
    }
}
