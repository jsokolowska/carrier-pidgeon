import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PolibiusTest {

    @Test
    public void keyTest()
    {
        Polibius p = new Polibius("Polibius");
        assertEquals(p.getKey(), "Polibius");

        Polibius p1 = new Polibius("Konstantynopol");
        assertEquals(p1.getKey(), "Konstantyn");
    }

    @Test
    public void cipherTest()
    {
        Polibius p = new Polibius("Kotek");
        String in = "Lala ma";
        String enc = p.encrypt(in);
        String dec = p.decrypt(enc);
        assertEquals(in, dec);

        Polibius p1 = new Polibius("Ambrozja");
        in = "Jasiek ma duza rodzine";
        enc = p1.encrypt(in);
        dec = p1.decrypt(enc);
        assertEquals(in, dec);

        String dec1 = p.decrypt(enc);
        assertNotEquals(dec, dec1);

    }
    
}
