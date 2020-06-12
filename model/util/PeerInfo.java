package model.util;

import controller.ContactInfoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Joanna Sokołowska
 */
public class PeerInfo {
    private final String ipAddress;
    private String name;
    private final int portNum;
    private final BlockingQueue<Parent> messages;
    private Parent contactInfo;
    private ContactInfoController controller;

    public PeerInfo(String ipAddress, String name, int portNum, BlockingQueue<Parent> messages) {
        this.ipAddress=ipAddress;
        this.name=name;
        this.portNum=portNum;
        if (messages==null){
            this.messages = new LinkedBlockingQueue<>();
        }else{
            this.messages = messages;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/contactInfo.fxml"));
            contactInfo = loader.load();
            controller = loader.getController();
            controller.makeContact(name);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public String getIpAddress(){
        return ipAddress;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPortNum() {
        return portNum;
    }
    public void addMessage(Parent msg){
        messages.add(msg);
    }
    public BlockingQueue<Parent> getMessages(){
        return messages;
    }
    public Parent getContactInfo(){
        return contactInfo;
    }
}
