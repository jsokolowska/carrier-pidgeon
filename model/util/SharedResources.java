package model.util;

import model.Message;
import model.Peer;

import java.net.Inet4Address;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Joanna Sokołowska
 */
public class SharedResources {
    public static BlockingQueue<Message> sentMsgQueue;
    public static BlockingQueue<Message> receivedMsgQueue;
    public static Peer peer;
    public static int version;
    static {
        version = 0;
        sentMsgQueue = new LinkedBlockingQueue<>();
        receivedMsgQueue = new LinkedBlockingQueue<>();
    }
}
