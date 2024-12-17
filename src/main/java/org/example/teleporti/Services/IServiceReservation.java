package org.example.teleporti.Services;

import org.example.teleporti.Entities.Reservation;

public interface IServiceReservation {
    public void ajout(Reservation newReservation);

    public void afficher();

    public void modifier(Reservation reservation);

    public void supprimer(Reservation reservation);
}
