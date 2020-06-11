package controller;

import controller.util.ResourcesManager;
import controller.util.SceneType;
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
        if(ResourcesManager.getCurrentScene()!= SceneType.MAIN){  //todo add condition if somebody is logged in
            currStage.setScene(ResourcesManager.getMainScene());
        }
    }

    @FXML
    private void logOut(){
        if(ResourcesManager.getCurrentScene() == SceneType.MAIN){
            //todo waiting: end session and switch scene to log in
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
