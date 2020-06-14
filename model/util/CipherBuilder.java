package model.util;

import model.Cipher;

/**
 * @author Joanna Sokołowska
 */
public class CipherBuilder {
    private Cipher cipher  = null;

    public Cipher getCipher() {
        return cipher;
    }
    public void setCipher(Cipher cipher){
        this.cipher = cipher;
    }
}

