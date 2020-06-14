package model;

import controller.DecryptController;
import controller.util.ThreadSafeResources;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.util.CipherBuilder;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionHandler extends Thread {
    private Socket PEER_SOCKET;
    private Cipher cipher;
    private DataInputStream in;
    private static final Logger LOGGER;
    private static final String helloSufix = ":ovrhenlo";

    static{
        LOGGER = Logger.getLogger(ConnectionHandler.class.getName());
    }

    public ConnectionHandler(Socket PEER_SOCKET){
        this.PEER_SOCKET = PEER_SOCKET;
    }

    @Override
    public void run() {
        try{
            in = new DataInputStream(new BufferedInputStream(PEER_SOCKET.getInputStream()));
            Message msg = readMsgFromClient();
            handleMessage(msg);
            in.close();

        }catch (IOException ioException){
            ioException.printStackTrace();
            LOGGER.setLevel(Level.ALL);
            LOGGER.info("Could not read message from Input Stream");
        }finally {
            try{
                PEER_SOCKET.close();
            }catch (IOException ioex){
                ioex.printStackTrace();
            }

        }
    }

    private Message readMsgFromClient(){
        String mess = "";
        String userNick = "";
        try{
            System.out.println("Reading Msg");
            userNick = in.readUTF();
            System.out.println("Nick: " + userNick);
            mess = in.readUTF();
            System.out.println("Mess " + mess);
        }catch (IOException ioException){
            ioException.printStackTrace();
            LOGGER.info("IOEXception while reading from peer socket");
        }

        return new Message(mess,userNick);
    }

    private void handleMessage(Message msg) throws IOException {
        if(isHelloMsg(msg)) {
            System.out.println("Received hello message. Tu będzie dodawanie kontaktu");
            String ipAddress = PEER_SOCKET.getRemoteSocketAddress().toString();
            ipAddress = ipAddress.replace("/", "");
            ipAddress = ipAddress.split(":")[0];
            String clean = msg.getMess().replaceAll("[^\\d.]", "");
            int port = Integer.parseInt(clean);

            ThreadSafeResources.addContactLater(msg.getUserNick().split(":")[0], ipAddress, port);

        }else{
            System.out.println("Before split: " + msg.getUserNick());
            String[] line = msg.getUserNick().split(":");
            String name = line[0];
            System.out.println("After split: " + name);
            boolean ciphered = false;
            if(line.length>1){
                if(line[1].equals("true")){
                    ciphered = true;
                }
            }
            msg.setUserNick(name);
            boolean finalCiphered = ciphered;
            Platform.runLater(()->{
                ThreadSafeResources.addMessage(msg, false, finalCiphered);
            });
        }

    }
    private boolean isHelloMsg(Message msg){
        return msg.getUserNick().endsWith(helloSufix);
    }

}