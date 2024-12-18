package org.example.teleporti;

import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {

    private static final UserController _userController = new UserController();

    public static void main(String[] args) {
        User user = null;
        try {
            user = new User(_userController.getSize() + 1,
                    "Abbassi",
                    "Chaima",
                    23,
                    "chaimaabbassi@gmail.com",
                    "chaima123",
                    "Admin"
            );

            _userController.truncate();
            _userController.ajout(user);
            _userController.afficher();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}