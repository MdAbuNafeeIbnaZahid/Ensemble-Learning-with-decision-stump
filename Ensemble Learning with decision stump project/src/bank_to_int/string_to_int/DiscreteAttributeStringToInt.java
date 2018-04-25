package bank_to_int.string_to_int;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my_util.MyUtil.isNumeric;

/**
 * Created by nafee on 4/16/18.
 */
public class DiscreteAttributeStringToInt extends AttributeStringToInt {



    int idx = 0;
    Map<String, Integer> map = new HashMap<>();

    public DiscreteAttributeStringToInt(List<String> stringList) {
        super(stringList);

        if (stringList == null)
        {
            throw new NullPointerException();
        }

        if ( isNumeric(stringList)  )
        {
            throw new IllegalArgumentException("DoubleStringToInt should be created instead");
        }

        for (String string : stringList)
        {
            if ( ! map.containsKey(string) )
            {
                map.put(string, idx++);
            }
        }
    }

    @Override
    public int getInt(String string) {

        if ( ! map.containsKey(string) )
        {
            throw new IllegalArgumentException("The current string is not found ever");
        }

        return map.get(string);
    }
}
