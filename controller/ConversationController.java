package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.IOException;


/**
 * @author Joanna Sokołowska
 */

public class ConversationController extends MenuController {
    @FXML
    private TextArea messageText;
    @FXML
    private Button sendButton;
    @FXML
    private Label conversationLabel;
    @FXML
    private VBox messageBox;
    /*todo in fxmlfile
    *  - make sure that no growth is necessary with msg hbox
    *  - change colors for sb msg
    *  - remove label on top?
    * */

    @FXML
    private void sendMsg(){
            //todo actually send msg
        //todo remove trailing blank characters, remove beginning blank characters
        String text = messageText.getText();
        if(text.trim().length()>0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/myMessage.fxml"));
            try{
                HBox msg = loader.load();
                MessageController msgCont = loader.getController();
                msgCont.makeMyMsg(text);
                messageBox.getChildren().add(msg);
                messageText.setText("");
            }catch(IOException ex){
                System.out.println("Could not load message");
            }
        }
    }

    @FXML
    private void onEnter(KeyEvent e){
        if(e.getCode()==KeyCode.ENTER){
            //todo allow for shift+enter to happen
            sendMsg();
            e.consume();
        }

    }

}
