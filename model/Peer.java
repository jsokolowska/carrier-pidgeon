import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Peer {
    private String name;
    private int port;
    private ServerThread server;

    private final List<ClientThread> connections = new LinkedList<>();

    public Peer(String nick, int port)
    {
        this.name = nick;
        this.port = port;

    }

    public void connect(BufferedReader bufferedReader) throws IOException
    {
        System.out.println("Enter hostname:port you want to connect");
        System.out.println("or press p to pass");
        String input = bufferedReader.readLine();
        String[] values = input.split(" ");
        if(!input.equals("p"))
        {
            for (int i = 0; i < values.length; ++i)
            {
                String[] address = values[i].split(":");
                Socket socket = null;
                try
                {
                    socket = new Socket(address[0], Integer.valueOf(address[1]));
                    new ClientThread(socket).start();
                } catch (Exception e){
                    if( socket != null )
                        socket.close();
                    else
                        System.out.println("Invalid input");
                }
            }
        }
    }

    public void disconnectFrom(String hname)
    {/*
        for( Object obj : connections ){
            Peer p = (Peer) obj;
            String peerHost = p.getName();
            if(peerHost.equals(hname)) {
                //disconect
                connections.remove(obj);
            }
        }

        for( Object obj : children )
        {
            Peer peer = (Peer) obj;
            //jeżeli jest taki chanel na serverze
            //to zamknąć go
            //to do
        }*/
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
        Peer p = new Peer(setupValues[0], Integer.parseInt(setupValues[1]));
        p.connect(bufferedReader);
    }

}
