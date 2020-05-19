package controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * @author Joanna Sokołowska
 */
public class MenuController {
    @FXML
    private MenuItem menuHome;
    @FXML
    private MenuItem menuLogOut;
    @FXML
    private MenuItem menuQuit;
    @FXML
    private MenuBar menuBar;

    @FXML
    private void goHome(){
        System.out.println("Switching to home screen...");
        Stage currStage = (Stage) menuBar.getScene().getWindow();
        if(FXMLResources.currentScene != SceneTypes.CONTACTS){  //todo add condition if somebody is logged in
            FXMLResources.currentScene = SceneTypes.CONTACTS;
            currStage.setScene(FXMLResources.contactsScene);
        }
    }
    @FXML
    private void logOut(){
        System.out.println("Logging out...");
        //if(Session.isInProgress()){
        //      //log out
                //FXMLResources.currentScene = SceneTypes.LOGIN;
        //      change screen()
        //}else{
        // do nothing()
        //}
    }
    @FXML
    private void quit (){
        System.out.println("Quitiing...");
        Stage currStage = (Stage) menuBar.getScene().getWindow();
        currStage.close();
        //clean up resources and close all windows
    }
}