package controller;

import controller.util.FXMLResourcesManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ServerThread;

import java.io.IOException;

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
            }catch (IOException ex){
                incorrectText.setText("Could not start server");
            }
            clean();
            Stage currStage = (Stage) toMainButton.getScene().getWindow();
            currStage.setScene(FXMLResourcesManager.getMainScene());
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
