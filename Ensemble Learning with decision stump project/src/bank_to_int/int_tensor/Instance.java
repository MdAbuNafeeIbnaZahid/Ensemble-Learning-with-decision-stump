package bank_to_int.int_tensor;

import bank_to_int.BankToInt;
import bank_to_int.bank_tensor.BankInstance;
import bank_to_int.string_to_int.StringToIntConverter;

/**
 * Created by nafee on 4/16/18.
 */
public class Instance {

    int[] attributes;
    int label;

    public Instance(BankToInt bankToInt, BankInstance bankInstance)
    {
        attributes = new int[BankInstance.ATTRIBUTE_CNT];
        for (int i = 0; i < attributes.length; i++)
        {
            StringToIntConverter attributeStringToIntConverter = bankToInt.getAttributeStringToIntConverter(i);
            attributes[i] = attributeStringToIntConverter.getInt( bankInstance.getAttribute(i) );
        }


        StringToIntConverter typeStringToIntConverter = bankToInt.getTypeStringToIntConverter();
        label = typeStringToIntConverter.getInt( bankInstance.getLabel() );
    }

    @Override
    public String toString() {
        String ret = "";
        for ( int i = 0; i < attributes.length;  i++ )
        {
            ret += attributes[i] + " ";
        }

        ret += label;

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

    public int getLabel()
    {
        return label;
    }
}
