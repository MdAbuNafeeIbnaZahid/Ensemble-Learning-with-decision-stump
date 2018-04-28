package artificial_intelligence;

import bank_to_int.int_tensor.Instance;

import java.util.ArrayList;
import java.util.List;
import static my_util.MyUtil.checkNotNull;
/**
 * Created by nafee on 4/27/18.
 */
public class KFoldCrossValidationWithAdaBoost {

    private int foldCnt;
    private int boostingRoundCnt;
    List<Instance> instanceList;

    int stepSize;

    private double avgAccuracy;
    private double avgF1Score;

    public KFoldCrossValidationWithAdaBoost(int foldCnt, int boostingRoundCnt, List<Instance> instanceList) {
        checkNotNull(instanceList);


//        System.out.println(" instanceList.size() = " + instanceList.size());


        if ( foldCnt < 2 || foldCnt > instanceList.size() )
        {
            throw new IllegalArgumentException();
        }

        if ( boostingRoundCnt < 1 )
        {
            throw new IllegalArgumentException();
        }

        this.foldCnt = foldCnt;
        this.boostingRoundCnt = boostingRoundCnt;
        this.instanceList = instanceList;

        stepSize =   (instanceList.size() + foldCnt -1 ) / foldCnt ;


        makeAvgAccuracyAndF1Score();
    }


    private List<Instance> getFold(int foldIdx)
    {
        checkIfValidFoldIdx(foldIdx);

        return instanceList.subList( stepSize * foldIdx, Math.min(stepSize * (foldIdx + 1), instanceList.size() ) );
    }


    private List<Instance> getTestSet(int testFoldIdx)
    {
        checkIfValidFoldIdx(testFoldIdx);
        return getFold(testFoldIdx);
    }

    private List<Instance> getTrainSet(int testFoldIdx)
    {
        checkIfValidFoldIdx(testFoldIdx);

        List<Instance> trainSet = new ArrayList<>();
        for ( int i = 0; i < foldCnt; i++ )
        {
            if ( i == testFoldIdx )
            {
                continue;
            }

            List<Instance> fold = getFold(i);
            trainSet.addAll( fold );
        }

        return trainSet;
    }

    private Testing doAndGetTesting( int testFoldIdx )
    {
        checkIfValidFoldIdx(testFoldIdx);

        List<Instance> trainSet = getTrainSet(testFoldIdx);
//        System.out.println( "trainSet.size() = " + trainSet.size() );



        List<Instance> testSet = getTestSet(testFoldIdx);
//        System.out.println(" testSet.size() =  " + testSet.size() );

        assert trainSet.size() + testSet.size() == instanceList.size();


        AdaBoostAlgorithm adaBoostAlgorithm = AdaBoostAlgorithm.getAdaBoostWithDecisionStump(boostingRoundCnt);


        Hypothesis hypothesisFromAdaBoost = adaBoostAlgorithm.adaBoost(trainSet);
        Testing testing = new Testing(hypothesisFromAdaBoost, testSet);

        return testing;
    }

    private void checkIfValidFoldIdx(int foldIdx)
    {
        if ( foldIdx < 0 || foldIdx >= foldCnt )
        {
            throw new IllegalArgumentException();
        }
    }

    private List<Testing> getTestingList()
    {
        List<Testing> testingList = new ArrayList<>();
        for (int i = 0; i < foldCnt; i++)
        {
            Testing testing = doAndGetTesting(i);
            testingList.add( testing );
        }
        return testingList;
    }

    private void makeAvgAccuracyAndF1Score()
    {
        List<Testing> testingList = getTestingList();

        double accuracySum = 0;
        double f1ScoreSum = 0;

        for (Testing testing : testingList)
        {
            accuracySum += testing.getAccuracy();
//            System.out.println("accuracySum = " + accuracySum);


            f1ScoreSum += testing.getF1Score();
//            System.out.println(" f1ScoreSum = " + f1ScoreSum);
        }

        avgAccuracy = accuracySum / foldCnt;
        avgF1Score = f1ScoreSum / foldCnt;
    }

    public double getAvgAccuracy() {
        return avgAccuracy;
    }

    public double getAvgF1Score() {
        return avgF1Score;
    }
}
