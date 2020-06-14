package model;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread
{
    private String userName;
    private static int port;
    private Socket socket;
    private DataOutputStream out;

    public int getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public ClientThread( String userName, Socket socket, int port)
    {
        this.userName = userName;
        this.socket = socket;
        this.port = port;
    }

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    public ClientThread(String userName)
    {
        this.userName = userName;
    }

    @Override
    public void run(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while(true)
            {
                connect(bufferedReader);
            }
        }
        catch (IOException e) {
            System.out.println("IO Exception!");
            e.printStackTrace();
        }
    }

    public void connect(BufferedReader bufferedReader) throws IOException
    {
        String hostname = "localhost";
        System.out.println("Give port nr of sb u want to send msg to");
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
                Message msg = new Message(line, userName);
                DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());
                socketOut.writeUTF(userName + ":");
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

    public Message createMess(BufferedReader bufferedReader) throws IOException {
        System.out.println("Type your message");
        String input = bufferedReader.readLine();
        return new Message(input, getUserName());
    }
}