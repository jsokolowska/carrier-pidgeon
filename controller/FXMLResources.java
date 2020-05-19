package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * @author Joanna Sokołowska
 */
public class FXMLResources {
    public static Scene loginScene;
    public static Scene signupScene;
    public static Scene contactsScene;
    public static Scene conversationScene;
    public static Scene newConnectionScene;
    public static SceneTypes currentScene;

    public void loadAllResources () throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("/resources/logIn.fxml"));
        Parent signup = FXMLLoader.load(getClass().getResource("/resources/signUp.fxml"));
        Parent contact = FXMLLoader.load(getClass().getResource("/resources/contacts.fxml"));
        Parent conversation = FXMLLoader.load(getClass().getResource("/resources/conversation.fxml"));
        Parent newConnection = FXMLLoader.load(getClass().getResource("/resources/newConnection.fxml"));

        loginScene = new Scene(login);
        signupScene = new Scene(signup);
        contactsScene = new Scene(contact);
        conversationScene = new Scene(conversation);
        newConnectionScene = new Scene(newConnection);
    }
}
