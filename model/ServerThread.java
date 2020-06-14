package model;

import controller.util.ThreadSafeResources;
import model.util.PeerInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private static final Logger LOGGER;
    private int port;
    static{
        LOGGER = Logger.getLogger(ServerThread.class.getName());
    }

    @Override
    public void  run(){
        try{
            while(true){
                Socket clientSocket = serverSocket.accept();
                new ConnectionHandler(clientSocket).start();
            }
        }catch (IOException ex){
            LOGGER.setLevel(Level.ALL);
            LOGGER.info("Error while listening on port " + port);
        }


    }
    public ServerThread(int port, PeerInfo pinfo){
        try{
            serverSocket = new ServerSocket(port);
            this.port = serverSocket.getLocalPort();
            pinfo.setPortNum(this.port);
            ThreadSafeResources.setPort(this.port);
        }catch (IOException ex){
            LOGGER.setLevel(Level.SEVERE);
            LOGGER.info("Could not create server socket");
            System.exit(-1);
        }

    }

    public String toString(){
        return "ServerThread on port " + port;
    }

}