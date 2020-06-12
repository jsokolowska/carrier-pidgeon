package controller;

import controller.util.FXMLResourcesManager;
import controller.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * @author Joanna Sokołowska
 */

public abstract class MenuController {
    //todo - rethink how menu should look like
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
        Stage currStage = (Stage) menuBar.getScene().getWindow();
        if(FXMLResourcesManager.getCurrentScene()!= SceneType.MAIN){
            currStage.setScene(FXMLResourcesManager.getMainScene());
        }
    }

    @FXML
    private void logOut(){
        if(FXMLResourcesManager.getCurrentScene() == SceneType.MAIN){
            //todo waiting: end session and switch scene to welcome
        }

    }

    @FXML
    private void quit (){
        Stage currStage = (Stage) menuBar.getScene().getWindow();
        currStage.close();
    }
}
