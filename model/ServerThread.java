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
    //private static FileHandler fhandl;
    //private static final Logger LOGGER;
    private final int PORT;
//    static{
//        LOGGER = Logger.getLogger(ServerThread.class.getName());
//    }

    @Override
    public void  run(){
        while(true){
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            new ConnectionHandler(clientSocket).start();
            //todo - finish connection without an exception
        }
    }
    public ServerThread(int PORT) throws  IOException{
        this.PORT = PORT;
        System.out.println("Starting server");
        serverSocket = new ServerSocket(PORT);
        System.out.println("Succesfull");

    }

    public String toString(){
        return "ServerThread on port " + PORT;
    }
}
