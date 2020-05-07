package model;

import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket peerSocket;
    public ConnectionHandler(Socket peerSocket){
        this.peerSocket = peerSocket;
    }

    @Override
    public void start() {
        //receive message
        Message msg = new Message("some text", 3);
        handleMsg(msg);
    }
    private String readMsgFromClient(){
        return null;
    }
    private void handleMsg(Message msg){
        System.out.println(msg.getMess());
    }
}
