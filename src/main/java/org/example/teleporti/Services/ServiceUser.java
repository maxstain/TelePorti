package org.example.teleporti.Services;

import org.example.teleporti.Entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceUser implements IServiceUser {

    private Statement ste;
    int size = 0;

    public ServiceUser(Connection con) {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void truncate() {
        String req = "truncate table users";
        try {
            ste.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void ajout(User newUser) {
        String req = "insert into users (nom, prenom, age, email, password, type, creation_date, update_date) values ('" +
                newUser.getNom() + "', '" +
                newUser.getPrenom() + "', '" +
                newUser.getAge() + "', '" +
                newUser.getEmail() + "', '" +
                newUser.getMotDePasse() + "', '" +
                newUser.getType() + "', '" +
                newUser.getCreationDate() + "', '" +
                newUser.getUpdateDate() +
                "')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void afficher() {
        String req = "select id, nom, prenom, age, email, password, type, creation_date, update_date from users";
        try {
            if (getSize() == 0) {
                System.out.println("Aucun utilisateur trouvé");
            } else {
                ResultSet res = ste.executeQuery(req);
                while (res.next()) {
                    System.out.println("ID: " + res.getInt("id") +
                            "\nNom: " + res.getString("nom") +
                            "\nPrenom: " + res.getString("prenom") +
                            "\nAge: " + res.getInt("age") +
                            "\nEmail: " + res.getString("email") +
                            "\nMot de passe: " + res.getString("password") +
                            "\nType: " + res.getString("type") +
                            "\nDate de création: " + res.getString("creation_date") +
                            "\nDate de modification: " + res.getString("update_date")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(User user) {
        String req = "update users set nom = '" +
                user.getNom() + "', prenom = '" +
                user.getPrenom() + "', age= '" +
                user.getAge() + "', email = '" +
                user.getEmail() + "', password = '" +
                user.getMotDePasse() + "', type = '" +
                user.getType() + "', update_date = '" +
                user.getUpdateDate() + "' where id = " +
                user.getId();
        try {
            ste.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(User user) {
        String req = "delete from users where id = " + user.getId();
        try {
            ste.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public int getSize() {
        String req = "select count(*) from users";
        try {
            ResultSet result = ste.executeQuery(req);
            while (result.next()) {
                size = result.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        String req = "select * from users where email='" + email + "' and password='" + password + "'";
        try {
            ResultSet res = ste.executeQuery(req);
            while (res.next()) {
                return new User(
                        res.getInt("id"),
                        res.getString("nom"),
                        res.getString("prenom"),
                        res.getInt("age"),
                        res.getString("email"),
                        res.getString("password"), // This maps to motDePasse in the User entity
                        res.getString("type")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
