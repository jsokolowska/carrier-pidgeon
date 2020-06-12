package controller;

import controller.util.ContactsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.util.PeerInfo;
import model.util.SharedResources;

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
    private void tryToConnect (){
        System.out.println("You clicked connect!");
        String hostIP = peerIP.getText();
        String name = peerName.getText();
        try{
            int portNum = Integer.parseInt(peerPort.getText());
            System.out.println("Creating new contact!");
            //SharedResources.peer.connect(hostIP, portNum);
            ContactsManager.addNewContact(name, hostIP, portNum, null);
            System.out.println("Closing scene!");
            Stage currStage = (Stage)connectButton.getScene().getWindow();
            currStage.close();

        }catch (NumberFormatException ex ){
            errorMsg.setText("Wrong credentials");
            errorMsg.setVisible(true);
            clean();
        }


        /*todo
        *  check if ip and port are correct
        *  send "welcome" message
        *  if not correct - display error message
        *  on succes close window*/
    }

    @FXML
    private void close(){
        clean();
        Stage currStage = (Stage) cancelButton.getScene().getWindow();
        currStage.close();
    }

    private void clean(){
        errorMsg.setText("");
        peerIP.setText("");
        peerName.setText("");
        peerPort.setText("");
    }
}
