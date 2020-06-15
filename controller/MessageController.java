package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cipher;
import model.Message;
import model.util.CipherBuilder;

import java.io.IOException;
import java.time.LocalTime;

/**
 * @author Joanna Sokołowska
 */

public class MessageController {
    @FXML
    private Text msgText;
    @FXML
    private Text timestamp;
    private String cipheredText;
    private String messText = null;
    private boolean tried;
    private boolean ciphered;


    public void makeMsg(String message){
        ciphered = false;
        msgText.setText(message);
        LocalTime time = LocalTime.now();
        String timestamp = String.format("%02d", time.getHour())+ ":" + String.format("%02d", time.getMinute());
        this.timestamp.setText(timestamp);
    }
    public void makeCipheredMsg(String message){
        ciphered = true;
        msgText.setText("<ciphered>");
        cipheredText = message;
        tried = false;
        LocalTime time = LocalTime.now();
        String timestamp = String.format("%02d", time.getHour())+ ":" + String.format("%02d", time.getMinute());
        this.timestamp.setText(timestamp);
    }

    @FXML
    private void decrypt(){
        if(ciphered){
            if(tried){
                if(messText==null){
                    messText = msgText.getText();
                }
                msgText.setText(messText +"[This message has already been decrypted]");
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/decrypt.fxml"));
                try{
                    Parent root = loader.load();

                    DecryptController controller = loader.getController();
                    CipherBuilder cipherBuilder = new CipherBuilder();
                    controller.setCipherBuilder(cipherBuilder);

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();

                    Cipher cipher = cipherBuilder.getCipher();
                    if(cipher != null){
                        msgText.setText(cipher.decrypt(cipheredText));
                        tried = true;
                    }
                }catch (IOException ex){
                    System.out.println("Could not load resources");
                }
            }
        }
    }

    public void makeMsg(Message msg){
        makeMsg(msg.getMess());
    }


}
