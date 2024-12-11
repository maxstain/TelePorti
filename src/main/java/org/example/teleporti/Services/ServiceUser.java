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
        String req = "insert into users (nom, prenom, age, email, password, type) values ('" +
                newUser.getNom() + "', '" +
                newUser.getPrenom() + "', '" +
                newUser.getAge() + "', '" +
                newUser.getEmail() + "', '" +
                newUser.getMotDePasse() + "', '" +
                newUser.getType() + "')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void afficher() {
        String req = "select id, nom, prenom, age, email, password, type from users";
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
                            "\nType: " + res.getString("type")
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
                user.getType() + "' where id = " +
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
}
