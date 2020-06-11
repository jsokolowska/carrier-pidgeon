import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolitaireTest {
    @Test
    public void cipherTest()
    {
        Solitaire s = new Solitaire();
        String mess = "Wiadomosc testowa!@$";
        String enc = s.encrypt(mess);
        String dec = s.decrypt(enc);

        assertEquals(dec, mess);
    }
}
