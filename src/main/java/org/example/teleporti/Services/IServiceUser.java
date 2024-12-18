package org.example.teleporti.Services;

import org.example.teleporti.Entities.User;

public interface IServiceUser {


    public void ajout(User newUser);


    public void afficher();


    public void modifier(User user);


    public void supprimer(User user);
}
