package controller;

import controller.util.ResourcesManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Joanna Sokołowska
 */

public class WelcomeController extends MenuController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField portField;
    @FXML
    private Button logIn;
    @FXML
    private Text incorrectText;

    public WelcomeController(){}


    @FXML
    private void tryToLogIn(){
            //todo start user and client threads, if sth goes wrong then display error message
           clean();
           Stage currStage = (Stage) logIn.getScene().getWindow();
           currStage.setScene(ResourcesManager.getMainScene());
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
