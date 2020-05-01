package model;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private int port;

    @Override
    public synchronized void  start (){
        try{
            serverSocket = new ServerSocket(port);

        }catch (IOException ignored){
            //log that sth went wrong
            //quit showing some error
        }
    }
    public ServerThread(int port){
        this.port = port;
    }
    /*
     * server info - peerinfo?
     * run()
     * constructor
     * wait for for conection
     * accept it
     * handle it
     *
     */
}
