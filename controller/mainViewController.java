package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * @author Joanna Sokołowska
 */

public class mainViewController extends MenuController {
    @FXML
    private TextArea messageText;
    @FXML
    private Button sendButton;
    @FXML
    private Label conversationLabel;
    @FXML
    private VBox messageBox; //todo manage growth

    public void setName(String peerName){
        conversationLabel.setText(peerName);
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
        System.out.println("You clicked to add new connection");
    }
}
