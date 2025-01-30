package org.example.teleporti.SceneControllers;

import eu.hansolo.toolbox.observables.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.MessageController;
import org.example.teleporti.Controllers.UserController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;
import org.example.teleporti.Utils.classes.Conversation;

import java.util.List;

public class MessagesViewController {

    private final AuthController authController = new AuthController();
    private final MessageController _messageController = new MessageController();
    private final UserController _userController = new UserController();

    @FXML
    protected ScrollPane conversationView = new ScrollPane();
    @FXML
    protected ListView<VBox> listView = new ListView<VBox>();

    @FXML
    protected ObservableList<VBox> conversationList = new ObservableList<>();

    protected Conversation selectedConversation = new Conversation();

    @FXML
    private Label welcome = new Label("");

    protected User currentUser;

    @FXML
    public void initialize() {

    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        welcome.setText("Welcome, " + currentUser.getPrenom() + "!");
        List<VBox> vboxList = _messageController.getAllCurrentUserConversations(currentUser.getId())
                .stream()
                .map(message -> {
                    boolean isSender = message.getSenderId() == currentUser.getId();
                    VBox vBox = new VBox();
                    vBox.getStyleClass().add("conversation-preview");
                    vBox.getChildren().add(
                            new Label(_userController.getUserById(isSender
                                    ? message.getRecieverId()
                                    : message.getSenderId()).getPrenom()));
                    String label = isSender ? "Vous: " : "";
                    vBox.getChildren().add(new Label(label + message.getMessageContent()));
                    vBox.onMouseClickedProperty().set(event -> {
                        try {
                            selectedConversation.setTalkingTo(_userController.getUserById(isSender
                                    ? message.getRecieverId()
                                    : message.getSenderId()).getPrenom());
                            selectedConversation.setMessages(_messageController.getConversationBetweenTwoUsers(currentUser.getId(), isSender
                                    ? message.getRecieverId()
                                    : message.getSenderId()));
                            VBox conversationBox = new VBox();
                            selectedConversation.getMessages().forEach(message1 -> {
                                VBox messageBox = new VBox();
                                messageBox.getStyleClass().add("message");
                                messageBox.setAlignment(
                                        isSender ? javafx.geometry.Pos.TOP_RIGHT : javafx.geometry.Pos.TOP_LEFT
                                );
                                messageBox.getChildren().add(
                                        new Label(_userController.getUserById(message1.getSenderId()).getPrenom()));
                                messageBox.getChildren().add(new Label(message1.getMessageContent()));
                                conversationBox.getChildren().add(messageBox);
                            });
                            conversationView.setContent(conversationBox);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    return vBox;
                })
                .toList();
        conversationList.addAll(vboxList);
        if (!conversationList.isEmpty()) {
            listView.getItems().addAll(conversationList);
        }
        conversationView.setContent(new Label("Select a conversation"));
    }

    @FXML
    public void onGoToProfile() {
        try {
            Router.goToProfile(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onGoToMessages() {
        try {
            Router.goToMessages(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLogout() {
        System.out.println("User logged out.");
        try {
            authController.logout(currentUser.getId());
            Router.goToLogin(welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void sendMessage() {
        try {
            // TODO: Implement send message
            Router.goToMessages(currentUser, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}