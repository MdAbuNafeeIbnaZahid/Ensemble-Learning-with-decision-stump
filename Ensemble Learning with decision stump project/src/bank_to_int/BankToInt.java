package bank_to_int;

import bank_to_int.bank_tensor.BankInstance;
import bank_to_int.int_tensor.Instance;
import bank_to_int.string_to_int.StringToIntConverter;
import bank_to_int.string_to_int.StringToIntConverterFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nafee on 4/16/18.
 */
public class BankToInt {

    private StringToIntConverter[] attributeStringToIntConverters;

    private List<BankInstance> bankInstanceList;
    private StringToIntConverter labelStringToIntConverter = StringToIntConverterFactory.getLabelStringToInt();

    public BankToInt( List<BankInstance> bankInstanceList )
    {
        this.bankInstanceList = bankInstanceList;
        assignAttributeStringToIntConverter();
    }

    private void assignAttributeStringToIntConverter( )
    {
        attributeStringToIntConverters = new StringToIntConverter[BankInstance.ATTRIBUTE_CNT];
        for ( int i = 0; i < BankInstance.ATTRIBUTE_CNT; i++ )
        {
            List<String> attributeList = getSpecificAttributeList(i);
            attributeStringToIntConverters[i] = StringToIntConverterFactory.getStringToIntConverter(attributeList);
        }
    }

    private List<String> getSpecificAttributeList(int attributeIdx )
    {
        if ( attributeIdx < 0 || attributeIdx >= BankInstance.ATTRIBUTE_CNT )
        {
            throw new IllegalArgumentException();
        }

        List<String> specificAttributeList = new ArrayList<>();
        for ( BankInstance bankInstance : bankInstanceList )
        {
            String specificAttribute = bankInstance.getAttribute(attributeIdx);
            specificAttributeList.add(specificAttribute);
        }

        return specificAttributeList;
    }

    private List<String> getLabelList()
    {
        List<String> typeList = new ArrayList<>();
        for (BankInstance bankInstance : bankInstanceList)
        {
            String type = bankInstance.getLabel();
            typeList.add(type);
        }

        return typeList;
    }


    public StringToIntConverter getLabelStringToIntConverter() {
        return labelStringToIntConverter;
    }

    public StringToIntConverter getAttributeStringToIntConverter(int attributeIdx)
    {
        if (attributeIdx < 0 || attributeIdx >= attributeStringToIntConverters.length)
        {
            throw new IllegalArgumentException();
        }

        return attributeStringToIntConverters[attributeIdx];
    }

    public List<Instance> getInstanceList(  )
    {
        List<Instance> instanceList = new ArrayList<>();

        for ( BankInstance bankInstance : bankInstanceList )
        {
            Instance instance = new Instance(this, bankInstance);
            instanceList.add(instance);
        }

        return instanceList;
    }
}
