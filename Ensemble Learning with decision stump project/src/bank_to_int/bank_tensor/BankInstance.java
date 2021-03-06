package bank_to_int.bank_tensor;

import java.util.Arrays;

/**
 * Created by nafee on 4/16/18.
 */
public class BankInstance {

    public static final int ATTRIBUTE_CNT = 20;


    private String[] attributes;
    private String label;


    public BankInstance(String line) {
        if (line == null)
        {
            throw new NullPointerException("line can't be null");
        }

        String[] tokens = line.split(";");
        assert tokens.length == ATTRIBUTE_CNT+1; // attributes + label

        attributes = Arrays.copyOfRange(tokens, 0, ATTRIBUTE_CNT); // last element of tokens is not attribute
        // rather it is the label of the instance

        label = tokens[ tokens.length-1 ]; // last element of the token is the label
    }

    public String getLabel() {
        return label;
    }

    public String getAttribute(int idx)
    {
        if ( idx < 0 || idx >= attributes.length )
        {
            throw new IllegalArgumentException();
        }

        return attributes[idx];
    }
}
