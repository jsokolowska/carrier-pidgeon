package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Joanna Sokołowska
 */

public class LoginController extends MenuController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logIn;
    @FXML
    private Button toSignUp;
    @FXML
    Text incorrectText;

    public LoginController (){}


    @FXML
    private void tryToLogIn(){
//        System.out.println("You tried to log in with name: " + loginField.getText());
//        System.out.println("And here's your password: + " + passwordField.getText());
        //incorrectText.setText("Incorrect login or password!");
        int userId = 1;
        //get data
        //passwordHash = passwordField.getText().hash();
        //userId = Session.checkCredentials(loginField.getText(), passwordHash);
       if(userId == 0){
              incorrectText.setText("Incorrect login or password!");
       }else {
           clean();
           Stage currStage = (Stage) logIn.getScene().getWindow();
           currStage.setScene(FXMLResources.getContactsScene());
       }
    }

    @FXML
    private void moveToSignUpScene(){
        System.out.println("Changing to sign up screen");
        Stage currStage = (Stage) toSignUp.getScene().getWindow();
        currStage.setScene(FXMLResources.getSignupScene());
    }

    private void clean(){
        loginField.setText("");
        passwordField.setText("");
        incorrectText.setText("");
    }
}
