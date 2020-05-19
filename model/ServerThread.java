package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joanna Sokołowska
 */

public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private static FileHandler fhandl;
    private static final Logger LOGGER;
    private final int PORT;
    static{
        LOGGER = Logger.getLogger(ServerThread.class.getName());
    }

    @Override
    public void  start(){
        try{
            serverSocket = new ServerSocket(PORT);
            while(true){
                Socket clientSocket = serverSocket.accept();
                new ConnectionHandler(clientSocket).start();
                //todo - finish connection without an exception
            }

        }catch (IOException e){
            if(fhandl==null){
                try {
                    fhandl = new FileHandler(ServerThread.class.getName());
                } catch (IOException exception) {
                    e.printStackTrace();
                }
            }

            LOGGER.setLevel(Level.SEVERE);
            LOGGER.info("Log directed to file");
            LOGGER.info("Could not create server Socket:" + this);
            System.out.println("Could not create Server Thread -quiting?");
        }
    }
    public ServerThread(int PORT){
        this.PORT = PORT;
    }

    public String toString(){
        return "ServerThread on port " + PORT;
    }
}
