package model;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionHandler extends Thread {
    private final Socket PEER_SOCKET;
    private DataInputStream in;
    private static final Logger LOGGER;
    private static FileHandler fhandl;
    static{
        LOGGER = Logger.getLogger(ConnectionHandler.class.getName());
        fhandl = null;
    }
    public ConnectionHandler(Socket PEER_SOCKET){
        this.PEER_SOCKET = PEER_SOCKET;
        createFileHandler(); //it may be moved somewhere else for better error handling and cleaner code
    }

    @Override
    public void start() {
        try{
            in = new DataInputStream(new BufferedInputStream(PEER_SOCKET.getInputStream()));

            Message msg = readMsgFromClient();
            handleMessage(msg);
            in.close();

        }catch (IOException ioException){
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
    private static void createFileHandler(){
        if(fhandl==null){
            try{
                fhandl = new FileHandler(ConnectionHandler.class.getName());
                LOGGER.setLevel(Level.ALL);
                LOGGER.info("Error reading data from peer connection");
            }catch (IOException ioException){
                ioException.printStackTrace();
                //todo possibly close?
            }
        }
    }
    private Message readMsgFromClient(){
        String mess = "";
        int userId = 0;
        try{
            userId = in.readInt();
            mess = in.readUTF();
        }catch (IOException ioException){
            LOGGER.info("IOEXception while reading from peer socket");
        }

        return new Message(mess,userId);
    }
    private void handleMessage(Message msg){
        System.out.println(msg.getMess());
    }
}
