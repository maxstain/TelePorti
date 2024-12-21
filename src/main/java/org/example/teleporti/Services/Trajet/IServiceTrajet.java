package org.example.teleporti.Services.Trajet;


import org.example.teleporti.Entities.Trajet;

public interface IServiceTrajet {
    public void ajout(Trajet newTrajet);

    public void afficher();

    public void modifier(Trajet trajet);

    public void supprimer(Trajet trajet);
}
