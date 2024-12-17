package org.example.teleporti.Entities;

import java.util.Date;
import java.util.Objects;

public class Trajet {
    private int id;
    private int conducteurId;
    private String pointDepart;
    private String destination;
    private Date dateHeure;
    private int placesDisponibles;
    private float co2Economise;

    public Trajet(int id, int conducteurId, String pointDepart, String destination, Date dateHeure, int placesDisponibles, float co2Economise) {
        this.id = id;
        this.conducteurId = conducteurId;
        this.pointDepart = pointDepart;
        this.destination = destination;
        this.dateHeure = dateHeure;
        this.placesDisponibles = placesDisponibles;
        this.co2Economise = co2Economise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConducteurId() {
        return conducteurId;
    }

    public void setConducteurId(int conducteurId) {
        this.conducteurId = conducteurId;
    }

    public String getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(String pointDepart) {
        this.pointDepart = pointDepart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }

    public float getCo2Economise() {
        return co2Economise;
    }

    public void setCo2Economise(float co2Economise) {
        this.co2Economise = co2Economise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trajet trajet)) return false;
        return getId() == trajet.getId() && getConducteurId() == trajet.getConducteurId() && getPlacesDisponibles() == trajet.getPlacesDisponibles() && Float.compare(getCo2Economise(), trajet.getCo2Economise()) == 0 && Objects.equals(getPointDepart(), trajet.getPointDepart()) && Objects.equals(getDestination(), trajet.getDestination()) && Objects.equals(getDateHeure(), trajet.getDateHeure());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConducteurId(), getPointDepart(), getDestination(), getDateHeure(), getPlacesDisponibles(), getCo2Economise());
    }


}
