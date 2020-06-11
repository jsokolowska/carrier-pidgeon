package unittests;
import model.Cezar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CezarTest {

    @Test
    public void cipherTest()
    {
        Cezar c = new Cezar(7);
        String mess = "To jest test!";
        String enc = c.encrypt(mess);
        String dec = c.decrypt(enc);
        assertEquals(dec, mess);

        Cezar c1 = new Cezar(87);
        mess = "Jakis test do ponownego testu@!;";
        enc = c1.encrypt(mess);
        dec = c1.decrypt(enc);
        assertEquals(dec, mess);

        String dec1 = c.decrypt(enc);
        assertNotEquals(dec1, dec);
    }

}
