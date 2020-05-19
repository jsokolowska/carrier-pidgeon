package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Joanna Sokołowska
 */

public class SignUpController extends MenuController{
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button toLogIn;
    @FXML
    public Button signUp;
}
