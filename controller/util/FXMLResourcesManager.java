package controller.util;

import controller.ContactInfoController;
import controller.MessageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import model.Message;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Joanna Sokołowska
 */

public class FXMLResourcesManager {
    private static SceneType currentScene;
    private ContactInfoController controller;
    private Node contactInfo;
    private Parent message;
    private MessageController messageController;


    private static synchronized void setCurrentScene (SceneType type){
        currentScene = type;
    }
    private static synchronized SceneType getCurrentScene (){
        return currentScene;
    }
    public FXMLResourcesManager(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/contactInfo.fxml"));
        try{
            contactInfo = loader.load();
            controller = loader.getController();
        }catch (IOException ex){
            System.out.println("Could not load visuals");
            ex.printStackTrace();
        }
        loader = new FXMLLoader(getClass().getResource("/resources/sbMessage.fxml"));
        try{
            message = loader.load();
            messageController = loader.getController();
        }catch (IOException ex){
            System.out.println("Could not load visuals");
            ex.printStackTrace();
        }
    }

    public ContactInfoController getController(){
        return controller;
    }
    public Node getContactInfo(){
        return contactInfo;
    }
    public Node getMessage(){return message;}
    public MessageController getMessageController(){return messageController;}
}
