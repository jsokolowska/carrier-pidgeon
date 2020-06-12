package model.util;

import model.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Joanna Sokołowska
 */
public class SharedResources {
    public static BlockingQueue<Message> msgQueue;

    static {
        msgQueue = new LinkedBlockingQueue<>();
    }
}
