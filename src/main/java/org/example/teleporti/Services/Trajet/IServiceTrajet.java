package org.example.teleporti.Services.Trajet;


import org.example.teleporti.Entities.Trajet;

import java.util.List;

public interface IServiceTrajet {
    public void ajout(Trajet newTrajet);

    public Object afficher();

    public void modifier(Trajet trajet);

    public void supprimer(Trajet trajet);

    public List<Trajet> getTrajetsByUserId(int id);
}
