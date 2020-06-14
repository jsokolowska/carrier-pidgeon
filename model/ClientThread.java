package model;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ClientThread extends Thread
{

    private String userName;
    private static int port;
    private static String host;
    private Socket socket;
    private static DataInputStream clientInput;
    private static DataOutputStream clientOutput;

    public int getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public ClientThread( String userName, Socket socket, int port, String host)
    {
        this.userName = userName;
        this.host = host;
        this.socket = socket;
        this.port = port;
    }

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            System.out.println("Trying to connect to hostip:" + host + "on port:" + port);
            if (socket == null){
                socket = new Socket(host, port);
            }
            System.out.println("Connection succesfull!");

           /* clientInput = new DataInputStream(socket.getInputStream());
            clientOutput = new DataOutputStream(socket.getOutputStream());
            while()
            {
                //główne miejsce na wysyłanie i odbieranie
            }*/
        }
        catch (UnknownHostException e) {
            System.out.println("Cannot find host!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception!");
            e.printStackTrace();
        }
    }
}