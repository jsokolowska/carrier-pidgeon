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
            socket = new Socket(hostName, port);

            String username = ThreadSafeResources.getUsername();
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
        catch (IOException e) {
            System.out.println("IO Exception!");
            e.printStackTrace();
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
    public static void helloBack(String ipAddress, int port){
        try{
            Socket socket = new Socket(ipAddress, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output= new DataOutputStream(socket.getOutputStream());

            //send hello message
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
    public void connect(BufferedReader bufferedReader) throws IOException
    {
        String hostname = "localhost";
        String port = bufferedReader.readLine();
        int portNr;
        try{
            portNr = Integer.parseInt(port);
        }catch (NumberFormatException e) {
            System.out.println("Not a number");
            portNr = 5050;

        }
        try{
            socket = new Socket(hostname, portNr);
            System.out.println("Type your message");
            String line = bufferedReader.readLine();
            if (line != null){
                Message msg = new Message(line, hostName);
                DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());
                socketOut.writeUTF(hostName + ":");
                socketOut.writeUTF(line);
            }
        }catch (IOException ex){
            System.out.println("Could not connect");
        }finally {
            try{
                if(socket != null){
                    socket.close();
                }

            }catch (IOException ex){
                System.out.println("Closing socket");
            }
        }
//        Cipher cipher = null;
//        System.out.println("Enter hostname:port:cipher:key");
//        System.out.println("Cipher: C for Cezar, S for Solitaire, P for Polibius or N for NULL");
//        System.out.println("Key: for Cezar - shift number");
//        System.out.println("Key: for Solitaire - key is not required");
//        System.out.println("Key: for Polibius - string");
//        String input = bufferedReader.readLine();
//        if (input != null) {
//            String[] address = input.split(":");
//            int var = address.length;
//            boolean success = false;
//            if(var > 1)
//            {
//                socket = null;
//                try{
//                    socket = new Socket(address[0], Integer.valueOf(address[1]));
//                    success = true;
//                } catch (Exception e) {
//                    if( socket != null ) {
//                        socket.close();
//                    }else {
//                        System.out.println("Invalid input");
//                    }
//                    success = false;
//                }
//                System.out.println("Created socket on: " + address[0] +":"+ address[1]);
//            }
//
//            if(success) {
//                out = new DataOutputStream(System.out);
//                if (var > 2) {
//                    switch (address[2]) {
//                        case "C":
//                            int key = 0;
//                            if (var == 4)
//                                key = Integer.valueOf(address[3]);
//                            cipher = new Cezar(key);
//                            break;
//                        case "S":
//                            cipher = new Solitaire();
//                            break;
//                        case "P":
//                            String key1 = " ";
//                            if (var == 4)
//                                key1 = address[3];
//                            cipher = new Polibius(key1);
//                            break;
//                        case "N":
//                            cipher = null;
//                            break;
//                        default:
//                            System.out.println("Unknown input, Cipher is null");
//                            cipher = null;
//                    }
//                }
//
//                Message msg = createMess(bufferedReader);
//                if (cipher != null) {
//                    String encrypt = cipher.encrypt(msg.getMess());
//                    msg.setMess(encrypt);
//                }
//
//                try {
//                    OutputStream outputStream = socket.getOutputStream();
//                    PrintWriter outp = new PrintWriter(outputStream);
//                    outp.println("Helo");
//                    out.writeUTF(msg.getUserNick());
//                    System.out.println("Hanlo");
//                    out.writeUTF(msg.getMess());
//                } catch (IOException e) {
//                    System.out.println("IOException while sending");
//                }
//
//                out.close();
//            }
//        } else {
//            System.out.println("Invalid input");
//        }
    }

//    public Message createMess(BufferedReader bufferedReader) throws IOException {
//        System.out.println("Type your message");
//        String input = bufferedReader.readLine();
//        return new Message(input, getUserName());
//    }
}