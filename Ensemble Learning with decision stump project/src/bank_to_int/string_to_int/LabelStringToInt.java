package bank_to_int.string_to_int;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nafee on 4/25/18.
 */
public class LabelStringToInt extends StringToIntConverter {

    public final static String YES = "\"yes\"";  // In dataset Yes is  represented by  "yes" (with quotes)
    public final static String NO = "\"no\"";   // In dataset No is  represented by  "no" (with quotes)


    static Map<String, Integer> stringIntegerMap = new HashMap<>();
    static
    {
        stringIntegerMap.put(NO, 0);
        stringIntegerMap.put(YES, 1);
    }


    @Override
    public int getInt(String string) {

        assert stringIntegerMap.containsKey(string);
        return stringIntegerMap.get(string);
    }
}
