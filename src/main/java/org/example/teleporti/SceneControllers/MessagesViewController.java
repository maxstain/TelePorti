package org.example.teleporti.SceneControllers;

import eu.hansolo.toolbox.observables.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.example.teleporti.Controllers.AuthController;
import org.example.teleporti.Controllers.MessageController;
import org.example.teleporti.Entities.User;
import org.example.teleporti.Utils.Router;

import java.util.List;

public class MessagesViewController {

    private final AuthController authController = new AuthController();
    private final MessageController _messageController = new MessageController();

    @FXML
    protected VBox Conversation = new VBox();
    @FXML
    protected ScrollPane scrollPane = new ScrollPane();

    @FXML
    protected ObservableList<VBox> conversationList = new ObservableList<>();

    @FXML
    private Label welcome = new Label("");

    protected User currentUser;

    @FXML
    public void initialize() {

    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        welcome.setText("Welcome " + currentUser.getPrenom() + "!");
        List<VBox> vboxList = _messageController.getAllCurrentUserConversations(currentUser.getId())
                .stream()
                .map(message -> {
                    VBox vBox = new VBox();
                    vBox.getChildren().add(new Label(message.getMessageContent()));
                    return vBox;
                })
                .toList();
        conversationList.addAll(vboxList);
        scrollPane.setContent(conversationList.getFirst());
        Conversation.getChildren().add(new Label("Select a conversation"));
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

    public void selectConvo(MouseEvent mouseEvent) {

    }
}
