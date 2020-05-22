package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @author Joanna Sokołowska
 */

public class ContactsController extends MenuController{
    @FXML
    private Button newConnectionButton;

    @FXML
    private void connect (){
        Scene connectionScene = FXMLResources.getNewConnectionScene();
        Stage connectionStage = new Stage();
        connectionStage.setScene(connectionScene);
        connectionStage.initModality(Modality.APPLICATION_MODAL);
        connectionStage.show();
    }
}
