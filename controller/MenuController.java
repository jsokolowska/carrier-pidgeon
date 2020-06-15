package controller;

import controller.util.ThreadSafeResources;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * @author Joanna Sokołowska
 */

public abstract class MenuController {
    @FXML
    private MenuItem menuQuit;
    @FXML
    private MenuBar menuBar;


    @FXML
    private void quit (){
        ThreadSafeResources.disconnectFromAll();
        System.exit(0);
    }
}
