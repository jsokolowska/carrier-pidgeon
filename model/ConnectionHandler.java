package model;

import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket peerSocket;
    public ConnectionHandler(Socket peerSocket){
        this.peerSocket = peerSocket;
    }

    @Override
    public synchronized void start() {
        super.start();
    }
}
