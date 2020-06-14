package controller;

import controller.util.ContactsManager;
import controller.util.FXMLResourcesManager;
import controller.util.ThreadSafeResources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.util.PeerInfo;

import java.io.IOException;

/**
 * @author Joanna Sokołowska
 */

public class MainViewController extends MenuController {
    @FXML
    private VBox innerMessageBox;
    @FXML
    private TextArea messageText;
    @FXML
    private Button sendButton;
    @FXML
    private Text contactName;
    @FXML
    private Text IPAddress;
    @FXML
    private Text portNr;
    @FXML
    private VBox outerMessageBox;
    @FXML
    private VBox contactsBox;

    @FXML
    private void initialize(){

        ThreadSafeResources.setContactsRoot(contactsBox);
    }


    public void setName(String peerName){
        contactName.setText(peerName);
    }

    @FXML
    private void sendMsg(){
        //todo actually send msg
        String text = messageText.getText();
        text = text.trim();
        if(text.length() >0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/myMessage.fxml"));
            try{
                HBox msg = loader.load();
                MessageController msgCont = loader.getController();
                msgCont.makeMsg(text);
                outerMessageBox.getChildren().add(msg);
                messageText.setText("");
            }catch(IOException ex){
                System.out.println("Could not load message");
            }
        }
    }

    @FXML
    private void onEnter(KeyEvent e){
        if(e.getCode()== KeyCode.ENTER ){
            if(e.isShiftDown()){
                messageText.appendText("\n");
            }else{
                sendMsg();
                e.consume();
            }
        }
    }

    @FXML
    private void addNewConnection(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/newConnection.fxml"));
        try{
            Parent node = loader.load();
            Scene newConnection =  new Scene(node);
            Stage newConn = new Stage();
            newConn.setScene(newConnection);
            newConn.initModality(Modality.APPLICATION_MODAL);
            newConn.show();
            System.out.println("You clicked to add new connection");
        }catch (IOException ex){
            System.out.println("Could not get resource");
        }


    }

    public void setInfo (PeerInfo peerInfo){
        IPAddress.setText(peerInfo.getIpAddress());
        portNr.setText(Integer.toString(peerInfo.getPortNum()));
        contactName.setText("");
    }

}
