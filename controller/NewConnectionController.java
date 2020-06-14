package controller;

import controller.util.Contact;
import controller.util.ThreadSafeResources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ClientThread;

import java.io.IOException;


/**
 * @author Joanna Sokołowska
 */

public class NewConnectionController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button connectButton;
    @FXML
    private TextField peerIP;
    @FXML
    private TextField peerName;
    @FXML
    private TextField peerPort;
    @FXML
    private Text errorMsg;

    @FXML
    private void initialize(){
        errorMsg.setText("");
        clean();
    }

    @FXML
    private void tryToConnect (){
        String hostIP = peerIP.getText();
        String name = peerName.getText();
        try{
            int portNum = Integer.parseInt(peerPort.getText());
            String hostName = ClientThread.checkConnection(hostIP, portNum);
            if(hostName != null){
                System.out.println("Sucessfully connected to " + hostName);
                if(ThreadSafeResources.exists(hostName)){
                    errorMsg.setText("Contact already created");
                }else{
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/contactInfo.fxml"));
                        Parent contact = loader.load();
                        ContactInfoController controller = loader.getController();
                        controller.makeContact(name, false);
                        VBox mbox = FXMLLoader.load(getClass().getResource("/resources/innerMsgBox.fxml"));
                        Contact newContact = new Contact(controller, mbox, hostIP, portNum);
                        ThreadSafeResources.addContact(newContact, contact);

                    }catch (IOException ex){
                        System.out.println("Could not load resources");
                    }
                    System.out.println("Closing scene!");
                    Stage currStage = (Stage)connectButton.getScene().getWindow();
                    currStage.close();
                }

            }else{
                errorMsg.setText("Wrong credentials");
                errorMsg.setVisible(true);
                clean();
            }
        }catch (NumberFormatException ex ){
            errorMsg.setText("Wrong credentials");
            errorMsg.setVisible(true);
            clean();
        }
    }

    @FXML
    private void close(){
        clean();
        Stage currStage = (Stage) cancelButton.getScene().getWindow();
        currStage.close();
    }

    private void clean(){
        //errorMsg.setText("");
        peerIP.setText("");
        peerName.setText("");
        peerPort.setText("");
    }
}
