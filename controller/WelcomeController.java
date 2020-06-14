package controller;

import controller.util.ThreadSafeResources;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.ServerThread;
import model.util.PeerInfo;

/**
 * @author Joanna Sokołowska
 */

public class WelcomeController extends MenuController {
    @FXML
    private TextField usernameField;
    @FXML
    private Button toMainButton;
    @FXML
    private Text incorrectText;

    private PeerInfo peerInfo;



    public WelcomeController(){}

    @FXML
    private void toMain(){
        String name = usernameField.getText();
        peerInfo.setName(name);
        ThreadSafeResources.setUsername(name);
        ServerThread serverThread = new ServerThread(0, peerInfo);
        serverThread.start();
        Stage currStage = (Stage) toMainButton.getScene().getWindow();
        currStage.close();
        clean();
    }
    private void clean(){
        usernameField.setText("");
        incorrectText.setText("");
    }
    void setPeerInfo(PeerInfo pinfo){
        peerInfo = pinfo;
    }

    @FXML
    private void onEnter(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER){
            toMain();
        }
    }
}
