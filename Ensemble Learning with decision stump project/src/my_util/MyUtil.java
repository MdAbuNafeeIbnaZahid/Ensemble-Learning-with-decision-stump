package my_util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static double getSum( double[] doubleAr )
    {
        double sum = 0;
        for (double d : doubleAr)
        {
            sum += d;
        }
        return sum;
    }

    public static void normalize( double[] doubleAr )
    {
        if ( doubleAr == null )
        {
            throw new NullPointerException();
        }

        double sum = getSum( doubleAr );
        for ( int i = 0; i < doubleAr.length; i++ )
        {
            doubleAr[i] /= sum;
        }
    }

    public static Map<Integer, Integer> getFreqMap( List<Integer> integerList )
    {
        if ( integerList == null )
        {
            throw new NullPointerException();
        }
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (Integer integer : integerList)
        {
            freqMap.merge(integer, 1, Integer::sum);
        }
        return freqMap;
    }

    public static double getEntropy( List<Integer> integerList )
    {
        if ( integerList == null )
        {
            throw new NullPointerException();
        }

        double totalSize = integerList.size();
        double totalEntropy = 0;

        Map<Integer, Integer> freqMap = getFreqMap(integerList);
        for ( Integer freq : freqMap.values() )
        {
            double probability = ( (double)freq ) / totalSize;
            double entropy = - probability * Math.log(probability);
            totalEntropy += entropy;
        }
        
        return totalEntropy;

    }
}
