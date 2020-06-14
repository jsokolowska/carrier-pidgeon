package controller.util;

import controller.ContactInfoController;
import controller.MessageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.Message;

import java.io.IOException;

/**
 * @author Joanna Sokołowska
 */

public class Contact {
    private final String name;
    private ContactInfoController controller;
    private final VBox innerMessageBox;

    public String getName(){
        return name;
    }
    public Contact(ContactInfoController controller, VBox innerMessageBox){
        name = controller.getContactName();
        this.innerMessageBox = innerMessageBox;
        this.controller = controller;

    }
    public VBox getMessageRoot(){
        return innerMessageBox;
    }
    public void addMessage(Message msg, boolean mine){
        String location;
        if (mine){
            location = "/resources/myMessage.fxml";
        }else {
            location = "/resources/sbMessage.fxml";
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
            Node message = loader.load();
            MessageController controller = loader.getController();
            controller.makeMsg(msg);
            innerMessageBox.getChildren().add(message);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
