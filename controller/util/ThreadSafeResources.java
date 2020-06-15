package controller.util;

import controller.ContactInfoController;
import controller.MessageController;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Cipher;
import model.ClientThread;
import model.Message;
import org.jetbrains.annotations.NotNull;

import java.util.Dictionary;
import java.util.Enumeration;
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
    public static synchronized boolean isConnected (String contact){
        Contact sb = contacts.get(contact);
        if(sb!=null){
            return sb.isConnected();
        }else {
            return false;
        }
    }
    public static synchronized void disconnect (String contact){
        Contact sb = contacts.get(contact);
        if(sb!=null) {
            sb.disconnect();
        }
    }
    public static synchronized void disconnectFromAll (){
        Enumeration<String> keys = contacts.keys();
        while(keys.hasMoreElements()){
            Contact contact = contacts.get(keys.nextElement());
            String ipAddress = contact.getIpAddress();
            int port = contact.getPort();
            contact.disconnect();
            ClientThread.disconnect(ipAddress, port);
        }
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
        Contact contact = new Contact(controller, outerMsgBox, ipAddress, port);
        // addContact(contact, contactInfo);
    }
    public static void addContactLater(String contactName, String ipAddress, int port){
        if (!exists(contactName)){
            FXMLResourcesManager manager = new FXMLResourcesManager();
            ContactInfoController controller = manager.getController();
            Node contactInfo = manager.getContactInfo();
            controller.makeContact(contactName);
            Contact contact = new Contact(controller,outerMsgBox, ipAddress, port);
            addContactLater(contact, contactInfo);
        }

    }
    public static void addContactLater(@NotNull Contact contact, Node contactNode){
        if(!exists(contact.getName())){
            Platform.setImplicitExit(false);
            Platform.runLater(()->{
                addContactInfo(contactNode);
            });
            addContactName(contact.getName());
            contacts.put(contact.getName(), contact);
        }


    }

    private synchronized static void addContactName(String name){
        contactNames.add(name);
    }

    public static synchronized boolean exists(String contactName){
        return contactNames.contains(contactName);
    }

    public synchronized static void addMessage(Message msg, boolean mine, boolean ciphered){
        String name = msg.getUserNick();
        String currname = displayedContactName.getText();
        if(name.equals(currname)){
            FXMLResourcesManager manager = new FXMLResourcesManager();
            MessageController controller = manager.getMessageController();
            if (ciphered){
                controller.makeCipheredMsg(msg.getMess());
            }else{
                controller.makeMsg(msg.getMess());
            }
            Node message = manager.getMessage();
            outerMsgBox.getChildren().add(message);
        }else{
            Contact contact = contacts.get(msg.getUserNick());
            if(contact==null){
                System.out.println("Couldnt find contact by name [" + msg.getUserNick()+"]");
            }else{
                contact.addMessage(msg, mine, ciphered);
            }
        }


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

   public static void sendMessage(String text, Cipher cipher){
       String name = displayedContactName.getText();
       Contact contact = contacts.get(name);
       String ipAddress = contact.getIpAddress();
       int port = contact.getPort();
       new ClientThread(ipAddress, port, text, cipher).start();

   }
}
