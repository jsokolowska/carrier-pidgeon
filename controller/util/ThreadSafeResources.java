package controller.util;

import controller.ContactInfoController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Message;
import org.jetbrains.annotations.NotNull;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * @author Joanna Sokołowska
 */
public class ThreadSafeResources {
    private static String username;
    private static int port;
    private static VBox contactsRoot;
    private static VBox outerMsgBox;
    private static Text displayedContactName;
    private static final Dictionary <String, Contact> contacts;
    private static final LinkedList<String> contactNames;
    static {
        contacts = new Hashtable<>();
        contactNames = new LinkedList<>();
    }

    public static synchronized void setUsername(String name){
        username=name;
    }

    public static synchronized String getUsername(){
        return username;
    }
    public static synchronized void setPort(int portNr){
        port = portNr;
    }
    public static synchronized int getPort(){
        return port;
    }

    public static void addContact(@NotNull Contact contact, Node contactNode){
        //does not need to be synchronized as it only uses hashtable
        addContactInfo(contactNode);
        addContactName(contact.getName());
        contacts.put(contact.getName(), contact);
    }
    public static void addContact(String contactName, String ipAddress, int port){
        FXMLResourcesManager manager = new FXMLResourcesManager();
        ContactInfoController controller = manager.getController();
        Node contactInfo = manager.getContactInfo();
        controller.makeContact(contactName);
        Contact contact = new Contact(controller, manager.getMbox(), ipAddress, port);
        // addContact(contact, contactInfo);
    }
    public static void addContactLater(String contactName, String ipAddress, int port){
        FXMLResourcesManager manager = new FXMLResourcesManager();
        ContactInfoController controller = manager.getController();
        Node contactInfo = manager.getContactInfo();
        controller.makeContact(contactName);
        Contact contact = new Contact(controller, manager.getMbox(), ipAddress, port);
         addContactLater(contact, contactInfo);
    }
    public static void addContactLater(@NotNull Contact contact, Node contactNode){
        Platform.setImplicitExit(false);
        Platform.runLater(()->{
            System.out.println("Inside run later");
            addContactInfo(contactNode);
        });
        addContactName(contact.getName());
        contacts.put(contact.getName(), contact);

    }

    private synchronized static void addContactName(String name){
        contactNames.add(name);
    }

    public static synchronized boolean exists(String contactName){
        return contactNames.contains(contactName);
    }

    public synchronized static void addMessage(Message msg, boolean mine){
        Contact contact = contacts.get(msg.getUserNick());
        contact.addMessage(msg, mine);
    }
    public static synchronized void setRoots(VBox contactsRoot, VBox outerMsgBox, Text contactName){
        ThreadSafeResources.contactsRoot = contactsRoot;
        ThreadSafeResources.outerMsgBox = outerMsgBox;
        displayedContactName = contactName;

    }
    private static synchronized void addContactInfo(Node node){
        contactsRoot.getChildren().add(node);
    }

    public static synchronized void openConversation(String contactName){
        if(exists(contactName)){
            int howmany = outerMsgBox.getChildren().size();
            String currName = displayedContactName.getText();
            if(!contactName.equals(currName)){
                if(howmany>0){
                    if(!currName.equals("")){
                        Contact currContact = contacts.get(currName);
                        currContact.saveMessages(outerMsgBox);
                    }
                }

                Contact contact = contacts.get(contactName);
                if (contact.getMessages()!=null){
                    outerMsgBox.getChildren().setAll(contact.getMessages());
                }else{
                    outerMsgBox.getChildren().clear();
                }
                setContactName(contactName);
            }
        }
    }
    private static synchronized void setContactName(String name){
        displayedContactName.setText(name);
    }
}
