package controller;

import controller.util.FXMLResourcesManager;
import controller.util.ThreadSafeResources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Peer;
import model.ServerThread;
import model.util.PeerInfo;
import model.util.SharedResources;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Joanna Sokołowska
 */

public class WelcomeController extends MenuController {
    @FXML
    private TextField usernameField;
    //@FXML
    //private TextField portField;
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
}
