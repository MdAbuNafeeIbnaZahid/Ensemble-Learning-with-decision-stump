package bank_to_int.string_to_int;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nafee on 4/25/18.
 */
public class LabelStringToInt extends StringToIntConverter {

    public final static String YES_STRING = "\"yes\"";  // In dataset Yes is  represented by  "yes" (with quotes)
    public final static String NO_STRING = "\"no\"";   // In dataset No is  represented by  "no" (with quotes)


    public final static int YES_INT = 1;
    public final static int NO_INT = 0;


    static Map<String, Integer> stringIntegerMap = new HashMap<>();
    static
    {
        stringIntegerMap.put(NO_STRING, NO_INT);
        stringIntegerMap.put(YES_STRING, YES_INT);
    }


    @Override
    public int getInt(String string) {

        assert stringIntegerMap.containsKey(string);
        return stringIntegerMap.get(string);
    }
}
