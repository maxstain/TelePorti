package org.example.teleporti.Services.User;

import org.example.teleporti.Entities.User;

import java.util.List;

public interface IServiceUser {


    boolean ajout(User newUser);

    void afficher();

    void modifier(User user);

    List<User> rechercher(String nom);

    void supprimer(User user);

    int countByType(String type);

    int countByGovernerat(String gouvernerat);

    User getUserById(int id);

    List<User> getAllChauffeurs();

    List<User> getAllClients();

    List<String> getAllGovernerats();

    void createUsersTableInDatabase();
}
