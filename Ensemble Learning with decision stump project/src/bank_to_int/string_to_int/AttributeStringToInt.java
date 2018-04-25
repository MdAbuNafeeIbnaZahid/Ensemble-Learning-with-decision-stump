package bank_to_int.string_to_int;

import java.util.List;

/**
 * Created by nafee on 4/25/18.
 */
public abstract class AttributeStringToInt extends StringToIntConverter {


    private List<String> stringList;

    public AttributeStringToInt(List<String> stringList) {
        this.stringList = stringList;
    }




}
