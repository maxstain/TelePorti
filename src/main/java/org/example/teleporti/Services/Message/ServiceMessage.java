package org.example.teleporti.Services.Message;

import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.Message;
import org.example.teleporti.Entities.User;

import java.sql.*;
import java.util.UUID;

public class ServiceMessage implements IServiceMessage {

    private final UserController userController = new UserController();
    private Statement ste;
    int size = 0;

    public ServiceMessage(Connection con) {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * @param senderId
     * @param recieverId
     * @return
     */
    @Override
    public Message getMessageBySenderIdAndRecieverId(int senderId, int recieverId) {
        return new Message();
    }
}