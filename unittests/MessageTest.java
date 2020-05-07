package unittests;

import model.Message;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MessageTest {

    @Test
    public void correctMess()
    {
        Date nowDate = new Date();
        String mess = "Ala ma kota";
        Message m1 = new Message(mess, 23);
        String text = m1.getMess();
        assertEquals(text, mess);

        int text_l = m1.getLength();
        assertEquals(text_l, mess.length());

        int id = m1.getUserID();
        assertEquals(id, 23);

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String date = sdf.format(nowDate);
        assertEquals(date, m1.getSendTime());
    }

    //in order to make it easier, tooLong test requires max_length = 15
    /*
    @Test
    public void tooLong()
    {
        Message m1 = new Message ("Ala ma kota a kot ma Ale", 43);
        int text_l = m1.getLength();
        assertEquals(text_l, m1.maxLength);

        String text = m1.getMess();
        assertEquals(text, "Ala ma kota a k");
    }
    */
    
    @Test
    public void blankMess()
    {
        Message m1 = new Message("", 1);

        assertEquals(m1.getMess(), "");
    }


}
