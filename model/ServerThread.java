package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private static FileHandler fileHandler;
    private static Logger logger;
    private int port;
    static{
        logger = Logger.getLogger(ServerThread.class.getName());
    }

    @Override
    public void  start(){
        try{
            serverSocket = new ServerSocket(port);
            while(true){
                Socket clientSocket = serverSocket.accept();
                new ConnectionHandler(clientSocket).start();
            }

        }catch (IOException e){
            try {
                fileHandler = new FileHandler(ServerThread.class.getName());
            } catch (IOException exception) {
                e.printStackTrace();
            }
            logger.setLevel(Level.SEVERE);
            logger.info("Log directed to file");
            logger.info("Could not create server Socket:" + this);
            System.out.println("Could not create Server Thread -quiting?");
        }
    }
    public ServerThread(int port){
        this.port = port;
    }

    public String toString(){
        return "ServerThread on port " + port;
    }
}
