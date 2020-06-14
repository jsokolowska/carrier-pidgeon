package controller.util;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

    private static synchronized String getUsername(){
        return username;
    }

    public static void addContact(@NotNull Contact contact, Node contactNode){
        //does not need to be synchronized as it only uses hashtable
        addContactInfo(contactNode);
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
            return contact.getInnerMsgBox();
        }
        return null;
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
            Contact contact = contacts.get(contactName);
            VBox innerMsgBox = contact.getInnerMsgBox();
            outerMsgBox.getChildren().setAll(innerMsgBox);
            setContactName(contactName);
        }
    }
    private static synchronized void setContactName(String name){
        displayedContactName.setText(name);
    }
}
