package controller.util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import model.util.PeerInfo;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

/**
 * @author Joanna Sokołowska
 */
public class ContactsManager {
    private static final Dictionary<String, PeerInfo> allMessages;
    private static ObservableList<Node> contacts;
    //private static LinkedList<String> contacts;
    private static int version;
    static {
        //contacts = new LinkedList<>();
        allMessages = new Hashtable<>();
        version = 0;
    }

    public static void addNewContact(String name, String ipAddress, int port, BlockingQueue<Parent> messages){
        version ++;
        PeerInfo peer = new PeerInfo(ipAddress, name, port, messages);
        allMessages.put(name, peer);
        contacts.add(peer.getContactInfo());
        /*synchronized (contacts){
            contacts.add(name);
        }*/
    }

    public static BlockingQueue<Parent> getMessages(String name){
        return allMessages.get(name).getMessages();
    }
    public static PeerInfo getPeerInfo(String name){
        return allMessages.get(name);
    }
    public static int getVersion(){
        return version;
    }
    public static void setContacts(ObservableList<Node> allContacts){
        contacts = allContacts;
    }

}
