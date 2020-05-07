package model;

import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket peerSocket;
    public ConnectionHandler(Socket peerSocket){
        this.peerSocket = peerSocket;
    }

    @Override
    public void start() {
        //recieve message
        handleMsg(msg);
    }
    private String readMsgFromClient(){

    }
    private void handleMsg(String msg){
        System.out.println(msg);
    }
}
