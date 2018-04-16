package bank_to_int.string_to_int;

import java.util.List;

/**
 * Created by nafee on 4/16/18.
 */
public abstract class StringToIntConverter {

    private List<String> stringList;

    public StringToIntConverter(List<String> stringList) {
        this.stringList = stringList;
    }






    public abstract int getInt(String string);

}
