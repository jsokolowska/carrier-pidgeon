package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Joanna Sokołowska
 */

public class SignUpController extends MenuController{
    @FXML
    private PasswordField repeatPassField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button toLogIn;
    @FXML
    private Button signUpButton;

    @FXML
    private void moveToLogInScene(){
        Stage currStage = (Stage) toLogIn.getScene().getWindow();
        currStage.setScene(FXMLResources.getLoginScene());
    }

    @FXML
    private void tryToSignUp(){
        loginField.setText("");
        passwordField.setText("");
        //incorrectText.setText("");
        /*System.out.println("Changing to sign up screen");
        Stage currStage = (Stage) toLogIn.getScene().getWindow();
        currStage.setScene(FXMLResources.loginScene);*/
    }

    private void clean(){
        //todo implement clean up funxtion for SingUp and login controllers
    }
}
