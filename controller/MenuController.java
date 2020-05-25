package controller;

import controller.util.FXMLResources;
import controller.util.SceneTypes;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * @author Joanna Sokołowska
 */

public abstract class MenuController {
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
        if(FXMLResources.getCurrentScene()!= SceneTypes.CONTACTS){  //todo add condition if somebody is logged in
            currStage.setScene(FXMLResources.getContactsScene());
        }
    }
    @FXML
    private void logOut(){
        if(FXMLResources.getCurrentScene() == SceneTypes.CONTACTS || FXMLResources.getCurrentScene() == SceneTypes.CONVERSATION){
            //todo waiting: end session
        }
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
    }
}
