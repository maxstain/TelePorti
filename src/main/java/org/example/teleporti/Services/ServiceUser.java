package org.example.teleporti.Services;

import org.example.teleporti.Entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// La classe ServiceUser implémente l'interface IServiceUser et fournit des fonctionnalités pour gérer les entités User.
public class ServiceUser implements IServiceUser {

    private Statement ste; // Objet Statement utilisé pour exécuter les requêtes SQL
    int size = 0; // Variable pour stocker le nombre d'utilisateurs

    // Le constructeur initialise l'objet Statement en utilisant la connexion de base de données fournie.
    public ServiceUser(Connection con) {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode permettant de vider toutes les données de la table "users".
    public void truncate() {
        String req = "truncate table users"; // Requête SQL pour vider la table "users"
        try {
            ste.executeUpdate(req); // Exécution de la requête
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode pour ajouter un nouvel utilisateur dans la base de données.
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
            ste.executeUpdate(req); // Exécution de la requête pour insérer un utilisateur
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode pour afficher tous les utilisateurs dans la base de données.
    @Override
    public void afficher() {
        String req = "select id, nom, prenom, age, email, password, type, creation_date, update_date from users";
        try {
            if (getSize() == 0) { // Vérifie si la table "users" est vide
                System.out.println("Aucun utilisateur trouvé");
            } else {
                ResultSet res = ste.executeQuery(req); // Exécution de la requête et récupération des résultats
                while (res.next()) { // Parcours de chaque ligne du jeu de résultats
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

    // Méthode pour modifier les informations d'un utilisateur dans la base de données.
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
            ste.executeUpdate(req); // Exécution de la requête pour mettre à jour un utilisateur
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode pour supprimer un utilisateur de la base de données en fonction de son ID.
    @Override
    public void supprimer(User user) {
        String req = "delete from users where id = " + user.getId();
        try {
            ste.executeUpdate(req); // Exécution de la requête pour supprimer un utilisateur
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode pour obtenir le nombre total d'utilisateurs dans la base de données.
    public int getSize() {
        String req = "select count(*) from users"; // Requête SQL pour compter toutes les lignes dans la table "users"
        try {
            ResultSet result = ste.executeQuery(req); // Exécution de la requête et récupération du résultat
            while (result.next()) { // Récupération du nombre d'utilisateurs
                size = result.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0; // Retourne 0 si une erreur se produit
    }

    // Méthode pour récupérer un utilisateur par son email et son mot de passe.
    public User getUserByEmailAndPassword(String email, String password) {
        String req = "select * from users where email='" + email + "' and password='" + password + "'";
        try {
            ResultSet res = ste.executeQuery(req); // Exécution de la requête et récupération du résultat
            while (res.next()) { // Si un utilisateur correspondant est trouvé, crée et retourne un objet User
                return new User(
                        res.getInt("id"),
                        res.getString("nom"),
                        res.getString("prenom"),
                        res.getInt("age"),
                        res.getString("email"),
                        res.getString("password"), // Cela correspond à motDePasse dans l'entité User
                        res.getString("type")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; // Retourne null si aucun utilisateur correspondant n'est trouvé
    }
}
