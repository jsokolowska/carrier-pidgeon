package controller.util;

import model.Peer;
import model.util.PeerInfo;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author Joanna Sokołowska
 */
public class ThreadSafeResources {
    private static String username;
    public static synchronized void setUsername(String name){
        username=name;
    }
    private static synchronized String getUsername(){
        return username;
    }
}
