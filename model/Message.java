package model;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private String userNick;
    private String mess;
    private String sendTime;

    public Message (@NotNull String mess, @NotNull String nick)
    {
        this.userNick = nick;
        this.mess = mess;
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        this.sendTime = sdf.format(nowDate);

    }

    public String getMess() {
        return mess;
    }

    public String getUserNick() {
        return userNick;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setMess(String mess)
    {
        this.mess = mess;
    }

    public void printMess()
    {
        System.out.println("[" + getUserNick() + "]");
        System.out.println(getMess());
        System.out.println(getSendTime());
    }
}