package model;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Polibius implements Cipher{

    private String key;
    private int[][] table = new int[10][10];

    public Polibius(String key)
    {
        key = key.trim();
        if (key.length() > 10)
        {
            char[] ch = key.toCharArray();
            char[] fkey = new char[10];
            System.arraycopy(ch, 0, fkey, 0, 10);
            this.key = String.valueOf(fkey);
        }
        else
        {
            this.key = key;
        }
        createTable();
    }

    public String getKey(){
        return key;
    }


    private void createTable()
    {
        char[] keych = getKey().toCharArray();
        boolean already = false;
        //first row contains key
        for(int i = 0; i < key.length(); i++)
        {
            for (int j = 0; j < i; j++)
            {
                if ((int) keych[i] == table[0][j])
                {
                    already = true;
                    break;
                }
            }
            if(!already)
                table[0][i] = keych[i];
            already = false;
        }

        //rest of the table contains other characters
        int a = 32;
        for( int i = 1; i < 10; ++i)
        {
            for(int j = 0; j < 10; ++j)
            {
                while(key.contains(String.valueOf((char)a))){
                        ++a;
                }
                table[i][j] = a;
                ++a;
            }
        }
/*      to see if table is created successfully
        for(int i = 0; i < 10; i++)
        {
            String str = "";
            for(int j = 0; j < 10; ++j)
            {
                str = str.concat(String.valueOf(table[i][j]));
                str = str.concat("\t");
            }
            System.out.println(str);
        }
 */
    }

    @Override
    public String encrypt(@NotNull String text)
    {
        String result = "";

        for(int i = 0; i < text.length(); ++i)
        {
            int current = text.charAt(i);
            for(int j = 0; j < 10; ++j)
            {
                for(int k = 0; k < 10; ++k)
                {
                    if(current == table[j][k])
                    {
                        result = result.concat(String.valueOf(j));
                        result = result.concat(String.valueOf(k));
                        result = result.concat(" ");
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String decrypt(@NotNull String text)
    {
        String result = "";
        int curr, x, y;
        char[] tab = text.toCharArray();
        for(int i = 0; i+2 < tab.length; i=i+3)
        {
            x = (int)tab[i]-48;
            y = (int)tab[i+1]-48;
            curr = table[x][y];
            result = result.concat(String.valueOf((char)curr));
        }

        return result;
    }

}
