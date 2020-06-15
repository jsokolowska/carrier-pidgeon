package model;

import controller.util.ThreadSafeResources;
import javafx.scene.shape.Circle;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread
{
    private String hostName;
    private static int port;
    private Socket socket;
    private DataOutputStream out;
    private String message;
    private Cipher cipher;

    public int getPort() {
        return port;
    }

    public String getHostName() {
        return hostName;
    }

    public ClientThread(String hostName, Socket socket, int port)
    {
        this.hostName = hostName;
        this.socket = socket;
        this.port = port;
    }

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    public ClientThread(String hostName)
    {
        this.hostName = hostName;
    }

    public ClientThread(String hostName, int port, String message, Cipher cipher) {
        this.hostName = hostName;
        this.message = message;
        this.cipher = cipher;
        this.port = port;
        this.socket=null;
    }
    public ClientThread(Socket socket,int port, String message, Cipher cipher) {
        this.socket = socket;
        this.message = message;
        this.cipher = cipher;
        this.port = port;
    }
    public ClientThread(Socket socket, int port,String message) {
        this.socket = socket;
        this.message = message;
        this.cipher = null;
        this.port = port;
    }
    public ClientThread(String hostName,int port, String message) {
        this.hostName = hostName;
        this.message = message;
        this.cipher = null;
        this.port = port;
    }

    @Override
    public void run(){
        try{
            String destName = ThreadSafeResources.getCurrentContact();
            String username = ThreadSafeResources.getUsername();
            boolean isConnected = ThreadSafeResources.isConnected(destName);
            if(isConnected){
                socket = new Socket(hostName, port);


                Message msg = new Message(message, username);
                if (cipher!= null){
                    msg.setMess( cipher.encrypt(msg.getMess()));
                }
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                String line = "";
                if(cipher!=null){
                    line = msg.getUserNick() +":true";
                }else{
                    line = msg.getUserNick();
                }
                outputStream.writeUTF(line);
                outputStream.writeUTF(msg.getMess());
                outputStream.close();
                socket.close();
            }



        }catch (IOException ex) {
            System.out.println("IO Exception!");
        }
    }

    public static boolean checkConnection(String ipAddress, int port){ //todo change to boolean
        boolean res = false;
        try{
            Socket socket = new Socket(ipAddress, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output= new DataOutputStream(socket.getOutputStream());

            //send hello message
            String sufix = ":ovrhenlo";
            String helloMsg = ThreadSafeResources.getUsername() + sufix;
            output.writeUTF(helloMsg);
            output.writeUTF(Integer.toString(ThreadSafeResources.getPort()));
            res = true;
            input.close();
            output.close();
            socket.close();

        } catch (IOException ignored) {}
        return res;
    }
    public static void disconnect(String ipAddress, int port){ //todo change to boolean
        try{
            Socket socket = new Socket(ipAddress, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output= new DataOutputStream(socket.getOutputStream());

            //send bye message
            String sufix = ":ovrhenloBye";
            String helloMsg = ThreadSafeResources.getUsername() + sufix;
            output.writeUTF(helloMsg);
            output.writeUTF(Integer.toString(ThreadSafeResources.getPort()));
            input.close();
            output.close();
            socket.close();

        } catch (IOException ignored) {}
    }
    public static void helloBack(String ipAddress, int port){
        try{
            Socket socket = new Socket(ipAddress, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output= new DataOutputStream(socket.getOutputStream());

            //send helloback message
            String sufix = ":ovrhenloB";
            String helloMsg = ThreadSafeResources.getUsername() + sufix;
            output.writeUTF(helloMsg);
            output.writeUTF(Integer.toString(ThreadSafeResources.getPort()));
            input.close();
            output.close();
            socket.close();
        }catch (IOException ignored){
        }

    }
}