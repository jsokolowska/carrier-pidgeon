package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Message;
import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;

/**
 * @author Joanna Sokołowska
 */

public class ContactInfoController {
    @FXML
    private Text contactName;
    @FXML
    private Circle unreadMsg;
    @FXML
    private Text lastMsg;
    @FXML
    private Text lastTimestamp;
    @FXML
    private Button openButton;

    public void makeContact(String contactName, boolean isMsgRead, Message lastMsg){
        this.contactName.setText(contactName.trim());
        if(lastMsg != null){
            unreadMsg.setVisible(isMsgRead);
            this.lastMsg.setText(lastMsg.getMess().trim());
            //lastTimestamp.setText(lastMsg.getTimestamp());
        }else{
            unreadMsg.setVisible(false);
            this.lastMsg.setText("");
            this.lastTimestamp.setText("");
        }

    }

    public void makeContact(String contactName, boolean isMsgRead){
        makeContact(contactName, isMsgRead, null);
    }

    public void makeContact(String contactName){
        makeContact(contactName, false, null);
    }

    public void showNewMessage( @NotNull Message lastMessage){
        unreadMsg.setVisible(true);
        LocalTime time = LocalTime.now();
        String timestamp = String.format("%02d", time.getHour())+ ":" + String.format("%02d", time.getMinute());
        lastTimestamp.setText(timestamp);
        this.lastMsg.setText(lastMessage.getMess().trim());
    }
    @FXML
    private void openConversation(){
        Stage currStage = (Stage) openButton.getScene().getWindow();
        //todo load conversation screen according to peer info

    }

    public String getContactName() {
        return contactName.getText();
    }
}
