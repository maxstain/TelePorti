package org.example.teleporti.Services.User;

import org.example.teleporti.Entities.User;

public interface IServiceUser {


    public boolean ajout(User newUser);


    public void afficher();

    public void modifier(User user);


    public void supprimer(User user);
}
