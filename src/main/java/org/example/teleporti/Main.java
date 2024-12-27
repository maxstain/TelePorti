package org.example.teleporti;

import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;

public class Main {

    private static final UserController _userController = new UserController();

    public static void main(String[] args) {
        User user1 = new User(_userController.getSize() + 1,
                "Abbassi",
                "Chaima",
                23,
                "chaimaabbassi@gmail.com",
                "chaima123",
                "Chauffeur",
                "Tunis",
                "Tunis",
                "Tunis",
                "+21612345678"
        );
        User user2 = new User(_userController.getSize() + 1,
                "Chabchoub",
                "Firas",
                25,
                "firaschabchoub@gmail.com",
                "Firas123",
                "Admin",
                "Sfax",
                "Sfax",
                "Sfax",
                "+21612345678"
        );

        User user3 = new User(_userController.getSize() + 1,
                "Ben Rhouma",
                "Rached",
                24,
                "rachedbrhouma@gmail.com",
                "Rached123",
                "Client",
                "Tunis",
                "Tunis",
                "Tunis",
                "+21612345678"
        );

        _userController.truncate();
        _userController.ajout(user1);
        _userController.ajout(user2);
        _userController.ajout(user3);
        _userController.afficher();

    }

}