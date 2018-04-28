import artificial_intelligence.*;
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



        int[] kFoldArray = {5,10,20};
        int[] boostingRoundArray = {1,5,10,20,30};
        int fixedKFoldCnt = 10;
        int fixedBoostingRoundCnt = 30;


//        KFoldCrossValidationWithAdaBoost kFoldCrossValidationWithAdaBoost =
//                new KFoldCrossValidationWithAdaBoost(10, 10, labelBalancedInstanceList);


        System.out.println(" fixedKFoldCount = " + fixedKFoldCnt);
        System.out.println(" Varying boosting Round count ");
        System.out.println("\n\n");
        for (int boostingRoundCnt : boostingRoundArray)
        {
            KFoldCrossValidationWithAdaBoost kFoldCrossValidationWithAdaBoost =
                    new KFoldCrossValidationWithAdaBoost(fixedKFoldCnt, boostingRoundCnt, labelBalancedInstanceList);


            System.out.println( "boostingRoundCnt = " + boostingRoundCnt );
            System.out.println( " accuracy =  " + kFoldCrossValidationWithAdaBoost.getAvgAccuracy() );
            System.out.println("f1Score = " + kFoldCrossValidationWithAdaBoost.getAvgF1Score() );
            System.out.println("\n\n\n");
        }


        System.out.println("fixed Boosting round count = " + fixedBoostingRoundCnt);
        System.out.println(" Varying foldCnt ");
        System.out.println("\n\n");
        for ( int foldCnt : kFoldArray )
        {
            KFoldCrossValidationWithAdaBoost kFoldCrossValidationWithAdaBoost =
                    new KFoldCrossValidationWithAdaBoost(foldCnt, fixedBoostingRoundCnt, labelBalancedInstanceList);


            System.out.println( "foldCnt = " + foldCnt );
            System.out.println( "accuracy = " + kFoldCrossValidationWithAdaBoost.getAvgAccuracy() );
            System.out.println( "f1Score = " + kFoldCrossValidationWithAdaBoost.getAvgF1Score() );
            System.out.println("\n\n\n");
        }




    }
}
