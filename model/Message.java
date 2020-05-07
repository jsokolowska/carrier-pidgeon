package model;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    static final int maxLength = 15;
    private int userID;
    private int length;
    private String mess;
    private String sendTime;

    public Message (@NotNull String mess, @NotNull int user_id)
    {
        this.userID = user_id;
        int length = mess.length();

        if( length > maxLength )
        {
            char t_mess[] = mess.toCharArray();
            char temp[] = new char[maxLength];

            for(int i = 0; i < maxLength; ++i)
               temp[i] = t_mess[i];

            String new_mess = new String(temp);

            this.mess = new_mess;
            this.length = maxLength;
        }
        else
        {
            this.mess = mess;
            this.length = length;
        }

        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        this.sendTime = sdf.format(nowDate);

    }

    public int getLength() {
        return length;
    }

    public String getMess() {
        return mess;
    }

    public int getUserID() {
        return userID;
    }

    public String getSendTime() {
        return sendTime;
    }


}