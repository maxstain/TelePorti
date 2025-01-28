package org.example.teleporti.Services.Message;

import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.Message;
import org.example.teleporti.Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
     * @param newMessage
     */
    @Override
    public boolean ajout(Message newMessage) {
        return true;
    }

    /**
     * @return
     */
    @Override
    public List<Message> afficher() {
        return List.of();
    }

    /**
     * @param message
     */
    @Override
    public boolean modifier(Message message) {
        return true;
    }

    /**
     * @param message
     */
    @Override
    public boolean supprimer(Message message) {
        return true;
    }

    /**
     * @param senderId
     * @param recieverId
     * @return
     */
    @Override
    public Message getMessageBySenderIdAndRecieverId(int senderId, int recieverId) {
        String query = "SELECT * FROM messages WHERE senderId = ? AND recieverId = ? ORDER BY sentAt DESC";
        try (PreparedStatement pstmt = ste.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, recieverId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Message(
                        rs.getInt("id"),
                        rs.getString("messageContent"),
                        rs.getInt("senderId"),
                        rs.getInt("recieverId"),
                        rs.getDate("sentAt"),
                        rs.getDate("updatedAt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param currentUserId
     * @return
     */
    @Override
    public List<Message> getAllCurrentUserConversations(int currentUserId) {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM messages WHERE senderId = ? OR recieverId = ? ORDER BY sentAt DESC";
        try (PreparedStatement pstmt = ste.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, currentUserId);
            pstmt.setInt(2, currentUserId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(
                        rs.getInt("id"),
                        rs.getString("messageContent"),
                        rs.getInt("senderId"),
                        rs.getInt("recieverId"),
                        rs.getDate("sentAt"),
                        rs.getDate("updatedAt")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public void createMessagesTable() {
        String req = "create table messages(" +
                "id int(11) primary key auto_increment," +
                "messageContent text not null," +
                "senderId int(11) not null," +
                "recieverId int(11) not null," +
                "sentAt datetime," +
                "updatedAt datetime" +
                ")";
        try {
            ste.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        String req = "select count(*) from messages";
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

    public void truncate() {
    }

    public List<Message> rechercher(String search) {
        List<Message> messageList = new ArrayList<Message>();
        return messageList;
    }
}