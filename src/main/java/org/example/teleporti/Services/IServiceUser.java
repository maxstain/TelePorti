package org.example.teleporti.Services;

import org.example.teleporti.Entities.User;

import java.util.List;

public interface IServiceUser {


    public void ajout(User newUser);


    public void afficher();

    public List<User> afficherList();

    public void modifier(User user);


    public void supprimer(User user);
}
