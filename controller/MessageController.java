package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.time.LocalTime;

/**
 * @author Joanna Sokołowska
 */

public class MessageController {
    @FXML
    private Text sbMsgText;
    @FXML
    private Text sbTimestamp;
    @FXML
    private Text myMsgText;
    @FXML
    private Text myTimestamp;

    public void makeMyMsg(String message){
        myMsgText.setText(message);
        LocalTime time = LocalTime.now();
        String timestamp = time.getHour() + ":" + time.getMinute();
        myTimestamp.setText(timestamp);
    }

    public void makeSbMsg(String message){
        sbMsgText.setText(message);
        LocalTime time = LocalTime.now();
        String timestamp = time.getHour() + ":" + time.getMinute();
        sbTimestamp.setText(timestamp);
    }
}
