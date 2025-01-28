package org.example.teleporti;

import org.example.teleporti.Controllers.MessageController;
import org.example.teleporti.Controllers.ReservationController;
import org.example.teleporti.Controllers.TrajetController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.Message;
import org.example.teleporti.Entities.Reservation;
import org.example.teleporti.Entities.Trajet;
import org.example.teleporti.Entities.User;

public class Main {

    private static final UserController _userController = new UserController();
    private static final TrajetController _trajetController = new TrajetController();
    private static final ReservationController _reservationController = new ReservationController();
    private static final MessageController _messageController = new MessageController();

    public static void main(String[] args) {

        _userController.createUserTableInDatabase();
        _messageController.createMessagesTable();


        User user1 = new User(_userController.getSize() + 1, "Abbassi", "Chaima", 23, "chaimaabbassi@gmail.com", "chaima123", "Chauffeur", "Tunis", "Tunis", "Tunis", "12345678");
        _userController.ajout(user1);

        User user2 = new User(_userController.getSize() + 1, "Chabchoub", "Firas", 25, "firaschabchoub@gmail.com", "Firas123", "Admin", "Sfax", "Sfax", "Sfax", "12345678");
        _userController.ajout(user2);

        User user3 = new User(_userController.getSize() + 1, "Ben Rhouma", "Rached", 24, "rachedbrhouma@gmail.com", "rached123", "Client", "Tunis", "Tunis", "Tunis", "12345678");
        _userController.ajout(user3);

        User user4 = new User(_userController.getSize() + 1, "Belhaj Amor", "Ahmed", 25, "ahmedbamor@gmail.com", "ahmed123", "Client", "Tunis", "Tunis", "Tunis", "12345678");
        _userController.ajout(user4);

        Trajet trajet1 = new Trajet(_trajetController.getSize() + 1, user1.getId(), "Tunis", "Sousse", 3, 20, 50.0f);
        _trajetController.ajout(trajet1);

        Trajet trajet2 = new Trajet(_trajetController.getSize() + 1, user1.getId(), "Tunis", "Sfax", 4, 30, 70.0f);
        _trajetController.ajout(trajet2);

        Trajet trajet3 = new Trajet(_trajetController.getSize() + 1, user1.getId(), "Tunis", "Bizerte", 1, 10, 30.0f);
        _trajetController.ajout(trajet3);

        Reservation reservation1 = new Reservation(_reservationController.getSize() + 1, user3.getId(), trajet1.getId(), "En cours");
        _reservationController.ajout(reservation1);

        Reservation reservation2 = new Reservation(_reservationController.getSize() + 1, user4.getId(), trajet1.getId(), "En cours");
        _reservationController.ajout(reservation2);

        Reservation reservation3 = new Reservation(_reservationController.getSize() + 1, user3.getId(), trajet2.getId(), "En cours");
        _reservationController.ajout(reservation3);

        // _userController.truncate();
        _userController.afficher();
        // _trajetController.truncate();
        _trajetController.afficher();
        // _reservationController.truncate();
        _reservationController.afficher();

    }

}