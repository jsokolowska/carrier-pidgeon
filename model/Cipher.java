import java.io.IOException;

public interface Cipher {
    abstract String encrypt(String text);
    abstract String decrypt(String text);
}
