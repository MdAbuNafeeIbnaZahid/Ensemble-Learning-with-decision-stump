package artificial_intelligence;

import bank_to_int.int_tensor.Instance;

import java.util.List;
import static my_util.MyUtil.checkNotNull;

import static bank_to_int.string_to_int.LabelStringToInt.*;


/**
 * Created by nafee on 4/26/18.
 */
public class Testing {
    private Hypothesis hypothesis;
    private List<Instance> instanceList;

    private int correctPredictionCnt = 0;
    private int wrongPredictionCnt = 0;

    private int truePositiveCnt = 0;
    private int trueNegativeCnt = 0;

    private int falsePositiveCnt = 0;
    private int falseNegativeCnt = 0;

    double precision;
    double recall;
    double accuracy;

    double f1Score;

    public Testing(Hypothesis hypothesis, List<Instance> instanceList) {

        checkNotNull(hypothesis);
        checkNotNull(instanceList);

        this.hypothesis = hypothesis;
        this.instanceList = instanceList;

        predictAndCheck();
        calculateAccuracyPrecisionRecall();
        calculateF1Score();
    }


    private void predictAndCheck()
    {

        for (Instance instance : instanceList)
        {
            if ( (hypothesis.giveLabel(instance) == NO_INT ) && (instance.getLabel() == NO_INT) )
            {
                trueNegativeCnt++;
            }
            else if ( (hypothesis.giveLabel(instance) == NO_INT ) && (instance.getLabel() == YES_INT) )
            {
                falseNegativeCnt++;
            }
            else if ( (hypothesis.giveLabel(instance) == YES_INT ) && (instance.getLabel() == NO_INT) )
            {
                falsePositiveCnt++;
            }
            else if ( (hypothesis.giveLabel(instance) == YES_INT ) && (instance.getLabel() == YES_INT) )
            {
                truePositiveCnt++;
            }
            else
            {
                throw new Error(" All possible 4 cases failed ");
            }
        }

        correctPredictionCnt = trueNegativeCnt + truePositiveCnt;
        wrongPredictionCnt = falseNegativeCnt + falsePositiveCnt;

        assert (correctPredictionCnt + wrongPredictionCnt) == instanceList.size();
    }


    private void calculateAccuracyPrecisionRecall()
    {
        accuracy = (double) correctPredictionCnt / (correctPredictionCnt + wrongPredictionCnt);
        precision = (double) truePositiveCnt / ( truePositiveCnt + falsePositiveCnt );
        recall = (double) trueNegativeCnt / (truePositiveCnt + falseNegativeCnt);
    }

    private void calculateF1Score()
    {
        double precisionInverse = 1/precision;
        double recallInverse = 1/recall;
        f1Score = 2 / (precisionInverse + recallInverse);
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getF1Score() {
        return f1Score;
    }

    public Hypothesis getHypothesis() {
        return hypothesis;
    }

    public List<Instance> getInstanceList() {
        return instanceList;
    }

    public int getCorrectPredictionCnt() {
        return correctPredictionCnt;
    }

    public int getWrongPredictionCnt() {
        return wrongPredictionCnt;
    }

    public int getTruePositiveCnt() {
        return truePositiveCnt;
    }

    public int getTrueNegativeCnt() {
        return trueNegativeCnt;
    }

    public int getFalsePositiveCnt() {
        return falsePositiveCnt;
    }

    public int getFalseNegativeCnt() {
        return falseNegativeCnt;
    }

    public double getPrecision() {
        return precision;
    }

    public double getRecall() {
        return recall;
    }
}
