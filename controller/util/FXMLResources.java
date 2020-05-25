package controller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * @author Joanna Sokołowska
 */

public class FXMLResources {
    private static Scene loginScene;
    private static Scene signupScene;
    private static Scene contactsScene;
    private static Scene conversationScene;
    private static Scene newConnectionScene;
    private static SceneType currentScene;


    public void loadAllResources () throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("/resources/logIn.fxml"));
        Parent signup = FXMLLoader.load(getClass().getResource("/resources/signUp.fxml"));
        Parent contact = FXMLLoader.load(getClass().getResource("/resources/contacts.fxml"));
        Parent conversation =  FXMLLoader.load(getClass().getResource("/resources/conversation.fxml"));
        Parent newConnection = FXMLLoader.load(getClass().getResource("/resources/newConnection.fxml"));

        loginScene = new Scene(login);
        signupScene = new Scene(signup);
        contactsScene = new Scene(contact);
        conversationScene = new Scene(conversation);
        newConnectionScene = new Scene(newConnection);
    }

    public static Scene getLoginScene() {
        currentScene = SceneType.LOGIN;
        return loginScene;
    }

    public static Scene getSignupScene() {
        currentScene = SceneType.SIGN_UP;
        return signupScene;
    }

    public static Scene getContactsScene() {
        currentScene = SceneType.CONTACTS;
        return contactsScene;
    }

    public static Scene getConversationScene() {
        currentScene = SceneType.CONVERSATION;
        return conversationScene;
    }

    public static Scene getNewConnectionScene() {
        return newConnectionScene;
    }

    public static SceneType getCurrentScene() {
        return currentScene;
    }
}
