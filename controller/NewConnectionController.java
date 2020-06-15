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
import java.net.Inet4Address;
import java.net.UnknownHostException;


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
        try{
            int portNum = Integer.parseInt(peerPort.getText());
            String localhost="";
            try{
                localhost = Inet4Address.getLocalHost().toString();
            }catch (UnknownHostException ignored){}

            boolean success = false;
            if(!(portNum==ThreadSafeResources.getPort() && localhost.contains(hostIP))){
                success = ClientThread.checkConnection(hostIP, portNum);

            }
            if(success){
                Stage currStage = (Stage)connectButton.getScene().getWindow();
                currStage.close();

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
        peerIP.setText("");
        peerPort.setText("");
    }
}
