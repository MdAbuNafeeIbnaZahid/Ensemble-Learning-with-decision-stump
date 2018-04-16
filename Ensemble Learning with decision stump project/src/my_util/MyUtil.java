package my_util;

import java.util.List;

/**
 * Created by nafee on 4/16/18.
 */
public class MyUtil {
    public static boolean isNumeric(List<String> stringList )
    {
        if ( stringList == null )
        {
            throw new NullPointerException();
        }

        for (String string : stringList)
        {
            if ( string == null )
            {
                throw  new NullPointerException();
            }

            if ( ! isNumeric(string) )
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
