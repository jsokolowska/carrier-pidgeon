package controller;

import controller.util.FXMLResources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @author Joanna Sokołowska
 */

public class ContactsController extends MenuController{
    @FXML
    private Button newConnectionButton;
    @FXML
    private VBox contactBox;
    private FXMLLoader loader;

    @FXML
    private void initialize(){
        loader = new FXMLLoader(getClass().getResource("/resources/contactInfo.fxml"));
        /*todo (waiting)
        *  - load contact views according to info list
        */
    }
    @FXML
    private void connect (){
        Scene connectionScene = FXMLResources.getNewConnectionScene();
        Stage connectionStage = new Stage();
        connectionStage.setScene(connectionScene);
        connectionStage.initModality(Modality.APPLICATION_MODAL);
        connectionStage.show();
    }

    private void loadContactInfo(){
        try{
            Parent contact = loader.load();
            ContactInfoController controller = loader.getController();
            controller.makeContact("Some name");
            contactBox.getChildren().add(contact);
        }catch(IOException exception){
            System.out.println("Abandon all hope!");
        }
    }
}
