package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private void tryToConnect (){
        String newIP = peerIP.getText();
        String portNum = peerPort.getText();
        // todo validate data correctness and try to connect
        // IP.parse(String)
        // portNum.parsePort
        //try{
        //
        //connect(peerName.getText(), newIP,
        // if succes - clean & close window, add contact, else - display error msg
    }

    @FXML
    private void cancelConnection (){
        clean();
        Stage currStage = (Stage) cancelButton.getScene().getWindow();
        currStage.close();
    }

    private void clean(){
        peerIP.setText("");
        peerName.setText("");
        peerPort.setText("");
    }
}
