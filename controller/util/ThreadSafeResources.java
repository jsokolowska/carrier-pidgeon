package controller.util;

import controller.ContactInfoController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import model.Message;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * @author Joanna Sokołowska
 */
public class ThreadSafeResources {
    private static String username;
    private static VBox contactsRoot;
    private static final Dictionary <String, Contact> contacts;
    private static final LinkedList<String> contactNames;
    static {
        contacts = new Hashtable<>();
        contactNames = new LinkedList<>();
    }

    public static synchronized void setUsername(String name){
        username=name;
    }
    private static synchronized String getUsername(){
        return username;
    }
    public static void addContact(Contact contact){
        //does not need to be synchronized as it only uses hashtable
        addContactName(contact.getName());
        contacts.put(contact.getName(), contact);
    }
    private synchronized static void addContactName(String name){
        contactNames.add(name);
    }
    public static synchronized boolean exists(String contactName){
        return contactNames.contains(contactName);
    }
    public synchronized static VBox getMessageRoot(String contactName){
        Contact contact = contacts.get(contactName);
        if (contact != null){
            return contact.getMessageRoot();
        }
        return null;
    }
    public synchronized static void addMessage(Message msg, boolean mine){
        Contact contact = contacts.get(msg.getUserNick());
        contact.addMessage(msg, mine);
    }
    public static synchronized void setContactsRoot(VBox root){
        contactsRoot = root;
    }
    public static synchronized void addContactInfo(Node node){
        contactsRoot.getChildren().add(node);
    }
}
