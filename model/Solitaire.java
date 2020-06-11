import org.jetbrains.annotations.NotNull;

public class Solitaire implements Cipher {

    @Override
    public String encrypt(@NotNull String text)
    {
        String mess = "";
        char[] tab = text.toCharArray();
        int iter = 1;

        for (int i = 0; i < tab.length; ++i) {
            int k = tab[i];
            k -= 32;
            k += iter;
            k = k % 94;
            k += 32;
            mess = mess.concat(String.valueOf((char)k));
        }

        return mess;
    }

    @Override
    public String decrypt(@NotNull String text)
    {
        String mess = "";
        int iter = 1;
        char[] tab = text.toCharArray();

        for(int i = 0; i < tab.length; ++i)
        {
            int k = tab[i];
            k -= 32;
            k -= iter;
            while(k < 0)
                k += 94;
            k += 32;
            mess = mess.concat(String.valueOf((char)k));
        }

        System.out.println(mess);
        return mess;
    }
}
