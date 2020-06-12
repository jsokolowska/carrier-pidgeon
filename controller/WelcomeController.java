package controller;

import controller.util.FXMLResourcesManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ServerThread;
import model.util.SharedResources;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * @author Joanna Sokołowska
 */

public class WelcomeController extends MenuController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField portField;
    @FXML
    private Button toMainButton;
    @FXML
    private Text incorrectText;

    public WelcomeController(){}

    @FXML
    private void toMain(){
            /*todo
            *  verify if passed port is a number
            *  try to start server with provided port nr
            *  display error message in case of an error*/
        try {
            int port = Integer.parseInt(portField.getText());
            String user = usernameField.getText();
            try{
                ServerThread serverThread = new ServerThread(port);
                serverThread.start();
                //SharedResources.portNum=port;
                InetAddress add = Inet4Address.getLocalHost();
                //SharedResources.ipAddress=add.toString();
                clean();
                Stage currStage = (Stage) toMainButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/mainView.fxml"));
                Parent mainView = loader.load();
                MainViewController controller = loader.getController();
                controller.setInfo(add.toString(), Integer.toString(port));
                Scene mainScene = new Scene(mainView);
                currStage.setScene(mainScene);
            }catch (IOException ex){
                incorrectText.setText("Could not start server");
            }
        }catch (NumberFormatException ex){
            incorrectText.setText("Wrong credentials");
        }
    }
    private void clean(){
        usernameField.setText("");
        portField.setText("");
        incorrectText.setText("");
    }
    public void displayErrorMsg(String msg){
        incorrectText.setText(msg);
    }
}
