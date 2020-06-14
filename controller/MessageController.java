package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Message;

import java.time.LocalTime;

/**
 * @author Joanna Sokołowska
 */

public class MessageController {
    @FXML
    private Text msgText;
    @FXML
    private Text timestamp;


    public void makeMsg(String message){
        msgText.setText(message);
        LocalTime time = LocalTime.now();
        String timestamp = String.format("%02d", time.getHour())+ ":" + String.format("%02d", time.getMinute());
        this.timestamp.setText(timestamp);
    }

    public void makeMsg(Message msg){
        makeMsg(msg.getMess());
    }


}
