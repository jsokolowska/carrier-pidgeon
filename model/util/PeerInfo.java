package model.util;

import controller.ContactInfoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Peer;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Joanna Sokołowska
 */
public class PeerInfo {
    private String ipAddress;
    private String name;
    private int portNum;

    public PeerInfo(String ipAddress, String name, int portNum) {
        this.ipAddress=ipAddress;
        this.name=name;
        this.portNum=portNum;
    }
    public PeerInfo(){
        try{
            this.ipAddress = Inet4Address.getLocalHost().toString();
        }catch (UnknownHostException ex){
            System.out.println("Could not get localhost");
        }
        name = "unknown";
        portNum = 0;

    }

    public synchronized String getIpAddress(){
        return ipAddress;
    }
    public synchronized void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }
    public synchronized String getName() {
        return name;
    }
    public synchronized void setName(String name) {
        this.name = name;
    }
    public synchronized int getPortNum() {
        return portNum;
    }
    public synchronized void setPortNum(int portNum){
        this.portNum = portNum;
    }


    @Override
    public String toString() {
        return "PeerInfo{" +
                "ipAddress='" + ipAddress + '\'' +
                ", name='" + name + '\'' +
                ", portNum=" + portNum +
                '}';
    }
}
