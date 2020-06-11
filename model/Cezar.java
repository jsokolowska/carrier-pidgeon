import org.jetbrains.annotations.NotNull;

public class Cezar implements Cipher{
    private int key;

    public Cezar(int key)
    {
        this.key = key%94;
    }

    @Override
    public String encrypt(@NotNull String text)
    {
        String mess = "";
        char[] tab = text.toCharArray();
        for(int i = 0; i < tab.length; ++i)
        {
            int k = tab[i];
            k -= 32;
            k += key;
            k = k%94;
            k += 32;
            mess = mess.concat(String.valueOf((char)k));
        }

        return mess;
    }

    @Override
    public String decrypt(@NotNull String text)
    {
        String mess = "";

        char[] tab = text.toCharArray();
        for(int i = 0; i < tab.length; ++i)
        {
           int k = tab[i];
           k -= 32;
           k-= key;
           if(k < 0)
               k+= 94;
           k += 32;
           mess = mess.concat(String.valueOf((char)k));
        }

        return mess;
    }

}