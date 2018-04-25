package bank_to_int.int_tensor;

import bank_to_int.BankToInt;
import bank_to_int.bank_tensor.BankInstance;
import bank_to_int.string_to_int.StringToIntConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static my_util.MyUtil.checkNotNull;

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


        StringToIntConverter typeStringToIntConverter = bankToInt.getLabelStringToIntConverter();
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

    public int getAttributeCnt()
    {
        return attributes.length;
    }

    public static int getCommonAttributeCnt(List<Instance> instanceList)
    {
        checkNotNull(instanceList);
        if ( instanceList.isEmpty() )
        {
            return 0;
        }

        Instance firstInstance = instanceList.get(0);
        int firstInstanceAttributeCnt = firstInstance.getAttributeCnt();
        for ( Instance instance : instanceList )
        {
            int attributeCnt = instance.getAttributeCnt();
            if ( attributeCnt != firstInstanceAttributeCnt )
            {
                throw new Error(" instanceList has different attribute count ");
            }
        }

        return firstInstanceAttributeCnt;
    }



    public static List<Instance> getInstanceListOfSpecificLabel(List<Instance> instanceList, int label)
    {
        checkNotNull(instanceList);
        List<Instance> instanceListOfSpecificLabel = new ArrayList<>();
        for (Instance instance : instanceList)
        {
            if ( instance.getLabel() == label )
            {
                instanceListOfSpecificLabel.add(instance);
            }
        }
        return instanceListOfSpecificLabel;
    }

    public static int getCntOfSpecificLabel(List<Instance> instanceList, int label )
    {
        checkNotNull(instanceList);
        List<Instance> instanceListOfSpecificLabel = getInstanceListOfSpecificLabel(instanceList, label);
        return instanceListOfSpecificLabel.size();
    }

    public static List<Instance> getZeroOneBalancedInstanceList( List<Instance> instanceList )
    {
        checkNotNull(instanceList);

        List<Instance> balancedInstanceList = new ArrayList<>();

        int instanceCntWithZeroLabel = getCntOfSpecificLabel(instanceList, 0);
        int instanceCntWtihOneLabel = getCntOfSpecificLabel(instanceList, 1);

        assert instanceCntWithZeroLabel > instanceCntWtihOneLabel;

        List<Instance> instanceListWithZeroLabel = getInstanceListOfSpecificLabel(instanceList, 0); // Majority
        List<Instance> instanceListWithOneLabel = getInstanceListOfSpecificLabel(instanceList, 1); // Minority

        balancedInstanceList.addAll(instanceListWithOneLabel);

        Collections.shuffle(instanceListWithZeroLabel);
        List<Instance> sameSizeAsOneInstanceListWithZeroLabel = instanceListWithZeroLabel.subList(0,instanceCntWtihOneLabel);

        balancedInstanceList.addAll(sameSizeAsOneInstanceListWithZeroLabel);

        Collections.shuffle( balancedInstanceList );
        return balancedInstanceList;
    }
}
