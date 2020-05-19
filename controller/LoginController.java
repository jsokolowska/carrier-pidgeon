package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Joanna Sokołowska
 */

public class LoginController extends MenuController {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button logIn;
    @FXML
    public Button toSignUp;

    private Scene signUpScene = null;

    public LoginController (){
        try{
            Parent signUp = FXMLLoader.load(getClass().getResource("/resources/signUp.fxml"));
            signUpScene = new Scene(signUp);
        }catch (IOException ex){
            System.out.println("Couldn't load signUp screen");
        }
    }


    @FXML
    public void tryToLogIn(){
        System.out.println("You tried to log in with name: " + loginField.getText());
        //get data
        //validate data
        //if not right display message
        //if right change scene
    }

    @FXML
    public void moveToSignUpScene(){
        System.out.println("Changing to sign up screen");
        if (signUpScene != null){
            Stage loginStage = (Stage) toSignUp.getScene().getWindow();
            loginStage.setScene(signUpScene);
        }
    }
}
