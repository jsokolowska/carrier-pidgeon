package controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * @author Joanna Sokołowska
 */
public class MenuController {
    @FXML
    public MenuItem menuHome;
    @FXML
    public MenuItem menuLogOut;
    @FXML
    public MenuItem menuQuit;

    @FXML
    private void goHome(){
        System.out.println("Switching to home screen...");
        //change scene to connection scene if user is logged in
    }
    @FXML
    private void logOut(){
        System.out.println("Logging out...");
        //end current session
        //change to log in screen
    }
    @FXML
    private void quit (){
        System.out.println("Quitiing...");
        //clean up resources and close all windows
    }
}
