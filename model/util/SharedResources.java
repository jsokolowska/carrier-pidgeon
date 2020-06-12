package model.util;

import model.Message;

import java.net.Inet4Address;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Joanna Sokołowska
 */
public class SharedResources {
    public static BlockingQueue<Message> msgQueue;
    public static int portNum;
    public static String ipAddress;


    static {
        msgQueue = new LinkedBlockingQueue<>();
    }
}
