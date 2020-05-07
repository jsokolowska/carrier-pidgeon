package model;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private static FileHandler fileHandler;
    private static Logger logger;
    private int port;
    static{
        logger = Logger.getLogger(ServerThread.class.getName());
        fileHandler = new FileHandler(ServerThread.class.getName());
    }

    @Override
    public void  start throws IOException(){
        try{
            serverSocket = new ServerSocket(port);
            while(true){
                Socket clientSocket = serverSocket.accept();
                new ConnectionHandler(clientSocket).start();
            }

        }catch (IOException e){
            logServerCreationFailure("Could not create Server Thread: " + this);
            //System.out.println("Could not create Server Thread -quiting?");
            throw e;
        }
    }
    public ServerThread(int port){
        this.port = port;
    }

    private void logServerCreationFailure(String msg){
        logger = Logger.getLogger(ServerThread.class.getName());
        fileHandler =

    }
    public String toString(){
        return "ServerThread on port " + port;
    }
}
