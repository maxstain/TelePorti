package org.example.teleporti.Services.User;

import org.example.teleporti.Entities.User;

import java.util.List;

public interface IServiceUser {


    public boolean ajout(User newUser);


    public void afficher();

    public void modifier(User user);

    public List<User> rechercher(String nom);

    public void supprimer(User user);

    public int countByType(String type);

    public int countByGovernerat(String gouvernerat);
}
