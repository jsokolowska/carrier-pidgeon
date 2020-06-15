package controller;

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
    private void quit (){
        Stage currStage = (Stage) menuBar.getScene().getWindow();
        System.exit(0);
    }
}
