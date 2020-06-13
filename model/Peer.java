import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Peer {
    private String name;
    private int port;
    private ServerThread server;

    public Peer(String nick, int port)
    {
        this.name = nick;
        this.port = port;

    }

    public String getName(){
        return name;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter username and port number:");
        String[] setupValues = bufferedReader.readLine().split(" ");
        ServerThread serverThread = new ServerThread(Integer.parseInt(setupValues[1]));
        serverThread.start();
        ClientThread client = new ClientThread(setupValues[0]);
        client.start();
    }

}
