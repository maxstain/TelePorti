package org.example.teleporti.Services.Reservation;

import org.example.teleporti.Entities.Reservation;

import java.util.List;

public interface IServiceReservation {
    public void ajout(Reservation newReservation);

    public List<Reservation> afficher();

    public void modifier(Reservation reservation);

    public void supprimer(Reservation reservation);
}
