import java.io.*;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionHandler extends Thread {
    private Socket PEER_SOCKET;
    private Cipher cipher;
    private DataInputStream in;
    private static final Logger LOGGER;
    private static FileHandler fhandl;

    static{
        LOGGER = Logger.getLogger(ConnectionHandler.class.getName());
        fhandl = null;
    }

    public ConnectionHandler(Socket PEER_SOCKET){
        this.PEER_SOCKET = PEER_SOCKET;
        createFileHandler(); //it may be moved somewhere else for better error handling and cleaner code
    }

    @Override
    public void run() {
        try{
            System.out.println("Handling an incoming connection");
            in = new DataInputStream(new BufferedInputStream(PEER_SOCKET.getInputStream()));

            Message msg = readMsgFromClient();
            handleMessage(msg);
            in.close();

        }catch (IOException ioException){
            LOGGER.setLevel(Level.ALL);
            LOGGER.info("Could not read message from Input Stream");
        }finally {
            try{
                PEER_SOCKET.close();
            }catch (IOException ioex){
                ioex.printStackTrace();
            }

        }
    }

    private static void createFileHandler(){
        if(fhandl==null){
            try{
                fhandl = new FileHandler(ConnectionHandler.class.getName());
                LOGGER.setLevel(Level.ALL);
                LOGGER.info("Error reading data from peer connection");
            }catch (IOException ioException){
                ioException.printStackTrace();
                //todo possibly close?
            }
        }
    }

    private Message readMsgFromClient(){
        String mess = "";
        String userNick = "";
        try{
            userNick = in.readUTF();
            mess = in.readUTF();
        }catch (IOException ioException){
            LOGGER.info("IOEXception while reading from peer socket");
        }

        return new Message(mess,userNick);
    }

    private void handleMessage(Message msg) throws IOException {
        System.out.println("Recieved message from : " + msg.getUserNick());
        System.out.println("Choose a way to decode a message. You can only do it once!");
        chooseCipher();
        if(cipher != null)
        {
            String decrypt = cipher.decrypt(msg.getMess());
            msg.setMess(decrypt);
        }
        msg.printMess();
    }

    private void chooseCipher() throws IOException {
        System.out.println("Enter cipher:key");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        if(input != null){
            String[] address = input.split(":");
            switch(address[0])
            {
                case "C":
                    cipher = new Cezar(Integer.valueOf(address[1]));
                case "S":
                    cipher = new Solitaire();
                case "P":
                    cipher = new Polibius(address[1]);
                case "N":
                    cipher = null;
                default:
                    System.out.println("Unknown input, Cipher is null");
                    cipher = null;
            }
        } else {
            System.out.println("Not given cipher - setting NULL");
            cipher = null;
        }
        bufferedReader.close();
    }
}