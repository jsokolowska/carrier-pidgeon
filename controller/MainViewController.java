package controller;

import controller.util.ThreadSafeResources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import model.Cipher;
import model.ClientThread;
import model.util.CipherBuilder;
import model.util.PeerInfo;

import java.io.IOException;

/**
 * @author Joanna Sokołowska
 */

public class MainViewController extends MenuController {
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

        ThreadSafeResources.setRoots(contactsBox, outerMessageBox, contactName);
    }

    @FXML
    private void sendMsg(){
        //todo actually send msg
        if(!contactName.getText().equals("")){
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
                    ThreadSafeResources.sendMessage(text, null);

                }catch(IOException ex){
                    System.out.println("Could not load message");
                }
            }
        }else {
            messageText.setText("");
        }

    }

    @FXML
    private void cipherAndSend(){
        if(!contactName.getText().equals("")){
            String text = messageText.getText();
            text = text.trim();
            CipherBuilder cipherBuilder = new CipherBuilder();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/resources/cipher.fxml"));
            try{
                Parent root = loader1.load();
                CipherController ciphController = loader1.getController();
                ciphController.setCipherBuilder(cipherBuilder);
                Scene newScene = new Scene(root);
                Stage newStage = new Stage();
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setScene(newScene);
                newStage.showAndWait();

            }catch (IOException ex){
                System.out.println("Could not load visual resources");
            }

            if(text.length() >0 && cipherBuilder.getCipher()!=null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/myMessage.fxml"));
                try{
                    HBox msg = loader.load();
                    MessageController msgCont = loader.getController();
                    msgCont.makeMsg(text);
                    outerMessageBox.getChildren().add(msg);
                    messageText.setText("");

                    Cipher cipher = cipherBuilder.getCipher();
                    ThreadSafeResources.sendMessage(text, cipher);
                }catch(IOException ex){
                    System.out.println("Could not load message");
                }
            }
        }else {
            messageText.setText("");
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
