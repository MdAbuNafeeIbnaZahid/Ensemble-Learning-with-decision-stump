package bank_to_int.string_to_int;

import java.util.List;
import my_util.*;

/**
 * Created by nafee on 4/16/18.
 */
public class StringToIntConverterFactory {

    public static StringToIntConverter getStringToIntConverter(List<String> stringList)
    {
        if ( stringList == null )
        {
            throw new NullPointerException();
        }


        if ( MyUtil.isNumeric( stringList ) )
        {
            return new DoubleStringToInt(stringList);
        }
        else
        {
            return new TypeStringToInt(stringList);
        }
    }


}
