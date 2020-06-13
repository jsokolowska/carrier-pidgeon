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
        Message m1 = new Message (mess, "Jager");
        String text = m1.getMess();
        assertEquals(text, mess);

        String nick = m1.getUserNick();
        assertEquals(nick, "Jager");

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String date = sdf.format(nowDate);
        assertEquals(date, m1.getSendTime());
    }
    
    @Test
    public void blankMess()
    {
        Message m1 = new Message("", "Jager");

        assertEquals(m1.getMess(), "");
    }

    @Test
    public void printTest()
    {
        Message m1 = new Message("Testowa", "Jager");
        m1.printMess();
    }

}
