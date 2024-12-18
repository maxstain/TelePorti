package org.example.teleporti.Services;


import org.example.teleporti.Entities.Trajet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceTrajet implements IServiceTrajet {

    private Statement ste;
    int size = 0;

    public ServiceTrajet(Connection con) {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void truncate() {
        String req = "truncate table trajets";
        try {
            ste.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void ajout(Trajet newTrajet) {
        String req =
                "insert into trajets (conducteurId, pointDepart, destination, dateHeure, placesDisponibles ,co2Economise) values ('" +
                        newTrajet.getConducteurId() + "', '" +
                        newTrajet.getPointDepart() + "', '" +
                        newTrajet.getDestination() + "', '" +
                        newTrajet.getDateHeure() + "', '" +
                        newTrajet.getPlacesDisponibles() + "', '" +
                        newTrajet.getCo2Economise()
                        + "')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void afficher() {
        String req = "select id, conducteurId, pointDepart, destination, dateHeure, placesDisponibles, co2Economise from trajets";
        try {
            if (getSize() == 0) {
                System.out.println("Aucun trajet trouvé");
            } else {
                ResultSet res = ste.executeQuery(req);
                while (res.next()) {
                    System.out.println("ID: " + res.getInt("id") +
                            "\nConducteur ID: " + res.getInt("conducteurId") +
                            "\nPoint de départ: " + res.getString("pointDepart") +
                            "\nDestination: " + res.getString("destination") +
                            "\nDate et heure: " + res.getDate("dateHeure") +
                            "\nPlaces disponibles: " + res.getInt("placesDisponibles") +
                            "\nCO2 économisé: " + res.getFloat("co2Economise")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Trajet trajet) {
        String req = "update trajets set conducteurId = '" + trajet.getConducteurId() +
                "', pointDepart = '" + trajet.getPointDepart() +
                "', destination = '" + trajet.getDestination() +
                "', dateHeure = '" + trajet.getDateHeure() +
                "', placesDisponibles = '" + trajet.getPlacesDisponibles() +
                "', co2Economise = '" + trajet.getCo2Economise() +
                "' where id = " + trajet.getId();
        try {
            ste.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Trajet trajet) {
        String req = "delete from trajets where id = " + trajet.getId();
        try {
            ste.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public int getSize() {
        String req = "select count(*) from trajets";
        try {
            ResultSet result = ste.executeQuery(req);
            while (result.next()) {
                size = result.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
