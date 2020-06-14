package controller.util;

import controller.ContactInfoController;
import controller.MessageController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Joanna Sokołowska
 */

public class Contact {
    private final String name;
    private ContactInfoController controller;
    private final VBox outerMessageBox;
    private final String ipAddress;
    private final int port;
    private ObservableList<Node> messages;

    public String getName(){
        return name;
    }
    public Contact(ContactInfoController controller, VBox outerMessageBox, String ipAddress, int port){
        name = controller.getContactName();
        this.controller = controller;
        this.port=port;
        this.ipAddress=ipAddress;
        this.outerMessageBox = outerMessageBox;
        messages = null;
    }
    public ObservableList<Node> getMessages(){
        int how = 0;
        if(messages != null){
            how = messages.size();
        }
        return messages;
    }
    public void saveMessages(VBox outerMessageBox){
        if (outerMessageBox.getChildren()!= null){
            messages = FXCollections.observableArrayList(outerMessageBox.getChildren());
        }

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
            outerMessageBox.getChildren().add(message);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public int getPort(){
        return port;
    }
}
