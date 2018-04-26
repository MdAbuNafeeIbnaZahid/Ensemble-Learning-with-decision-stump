import artificial_intelligence.AdaBoost;
import artificial_intelligence.DecisionStumpLearning;
import artificial_intelligence.Testing;
import artificial_intelligence.WeightedMajority;
import bank_to_int.BankToInt;
import bank_to_int.bank_tensor.BankDatasetReader;
import bank_to_int.bank_tensor.BankInstance;
import bank_to_int.int_tensor.Instance;


import java.util.List;

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
        List<Instance> instanceList = bankToInt.getInstanceList();

        List<Instance> labelBalancedInstanceList = Instance.getZeroOneBalancedInstanceList(instanceList);
        int labelBalancedInstanceListSize = labelBalancedInstanceList.size();

        AdaBoost adaBoost = new AdaBoost();
        WeightedMajority weightedMajority = adaBoost.adaBoost(labelBalancedInstanceList.subList(0, labelBalancedInstanceListSize/2), new DecisionStumpLearning(), 1000);

        Testing testing = new Testing(weightedMajority, labelBalancedInstanceList);
        System.out.println( testing.getAccuracy() );
        System.out.println( testing.getF1Score() );

    }
}
