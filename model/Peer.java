package model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;


public class Peer {
    private String name;
    private int port;
    private final ServerThread server;

    private SocketChannel channel;

    private final List children = new LinkedList();
    private final List connections = new LinkedList();

    public Peer(Peer peer)
    {
        server = null;

        if (peer != null)
            children.add(peer);
    }

    public Peer(Peer peer, String nick, String host, int port, ServerThread server) throws IOException
    {
        this.name = nick;
        this.port = port;
        this.server = server;

        if( peer != null )
            children.add(peer);

        ServerSocket server_socket;
        InetSocketAddress address;
        Socket socket;
        try {
            server_socket = new ServerSocket();
            address = new InetSocketAddress(port);
            server_socket.bind(address);
            socket = server_socket.accept();
            System.out.println("Zglosil sie klient");
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void connect(String host, int port) throws IOException
    {
        //Connection conn = new connection(Inet.Addres.geyByName(host), port, this);
        //connections.add(conn);
        //new Thread(conn).start();
    }

    public void disconnectFrom(String hname)
    {
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
        }
    }

    public String getName(){
        return name;
    }


}
