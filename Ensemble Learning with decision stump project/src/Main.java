import bank_to_int.BankToInt;
import bank_to_int.bank_tensor.BankDatasetReader;
import bank_to_int.bank_tensor.BankInstance;
import bank_to_int.int_tensor.Instance;
import bank_to_int.int_tensor.InstanceWithWeight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nafee on 4/16/18.
 */
public class Main {
    public static void main(String[] args)  {


        String dataSetFileName = "bank-additional-full.csv";

        List<BankInstance> bankInstanceList = null;
        try
        {
            bankInstanceList = BankDatasetReader.getBankInstanceList(dataSetFileName);
        }
        catch (Exception e)
        {
            System.out.println("Failed to read the dataset");
        }


        BankToInt bankToInt = new BankToInt(bankInstanceList);
        List<Instance> instanceList = bankToInt.getInstanceList(bankInstanceList);


        List<InstanceWithWeight> instanceWithWeightList = InstanceWithWeight.getInstanceWithWeightList(instanceList);

        List<Double> weightList = InstanceWithWeight.getWeightList(instanceWithWeightList);

        System.out.println(weightList);





    }
}
