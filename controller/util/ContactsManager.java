package controller.util;

import javafx.scene.Parent;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Joanna Sokołowska
 */
public class ContactsManager {
    public static Dictionary<String, Collection<Parent>> allMessages;
    static {
        allMessages = new Hashtable<>();
    }

    public static void addNewContact(String name, Collection<Parent> messages){
        allMessages.put(name, messages);
    }

    public static Collection<Parent> getMessages(String name){
        allMessages.get(name);
        return null;
    }
}
