package bank_to_int.string_to_int;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static my_util.MyUtil.isNumeric;

/**
 * Created by nafee on 4/16/18.
 */
public class DoubleStringToInt extends AttributeStringToInt {

    public static final int NUMERIC_DIVISION_COUNT = 50;

    private double minVal;
    private double maxVal;
    private List<Double> doubleList;
    private double stepSize;



    public DoubleStringToInt(List<String> stringList) {
        super(stringList);

        if (stringList == null)
        {
            throw new NullPointerException();
        }

        if ( ! isNumeric(stringList)  )
        {
            throw new IllegalArgumentException("DiscreteAttributeStringToInt should be created instead");
        }

        doubleList = getDoubleList(stringList);
        Collections.sort(doubleList);

        minVal = doubleList.get(0);
        maxVal = doubleList.get( doubleList.size() - 1 );

        stepSize = (maxVal-minVal)/NUMERIC_DIVISION_COUNT;
    }

    private List<Double> getDoubleList(List<String> stringList)
    {
        if ( stringList == null )
        {
            throw new NullPointerException();
        }


        List<Double> doubleList = new ArrayList<>();
        for (String string : stringList)
        {
            if ( ! isNumeric(string) )
            {
                throw new Error(" Can't convert to Double ");
            }

            doubleList.add( Double.parseDouble(string) );
        }

        return doubleList;
    }


    @Override
    public int getInt(String string) {

        if ( string == null )
        {
            throw new NullPointerException();
        }

        if ( ! isNumeric(string) )
        {
            throw new Error("Not even a valid number");
        }

        double d = Double.parseDouble(string);
        return (int) ( (d-minVal)/stepSize );
    }
}
