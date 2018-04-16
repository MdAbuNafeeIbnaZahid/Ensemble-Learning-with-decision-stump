package bank_to_int.int_tensor;

import bank_to_int.BankToInt;
import bank_to_int.bank_tensor.BankInstance;
import bank_to_int.string_to_int.StringToIntConverter;

import java.util.Arrays;

/**
 * Created by nafee on 4/16/18.
 */
public class Instance {

    int[] attributes;
    int type;

    public Instance(BankToInt bankToInt, BankInstance bankInstance)
    {
        attributes = new int[BankInstance.ATTRIBUTE_CNT];
        for (int i = 0; i < attributes.length; i++)
        {
            StringToIntConverter attributeStringToIntConverter = bankToInt.getAttributeStringToIntConverter(i);
            attributes[i] = attributeStringToIntConverter.getInt( bankInstance.getAttribute(i) );
        }


        StringToIntConverter typeStringToIntConverter = bankToInt.getTypeStringToIntConverter();
        type = typeStringToIntConverter.getInt( bankInstance.getType() );
    }

    @Override
    public String toString() {
        String ret = "";
        for ( int i = 0; i < attributes.length;  i++ )
        {
            ret += attributes[i] + " ";
        }

        ret += type;

        return ret;
    }

    public int getAttribute(int attrIdx)
    {
        if (attrIdx < 0 || attrIdx >= attributes.length)
        {
            throw new IllegalArgumentException();
        }

        return attributes[attrIdx];
    }

    public int getType()
    {
        return type;
    }
}
