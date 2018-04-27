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

    public KFoldCrossValidationWithAdaBoost(int foldCnt, int boostingRoundCnt, List<Instance> instanceList) {
        checkNotNull(instanceList);

        if ( foldCnt < 1 || foldCnt > instanceList.size() )
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

    private void checkIfValidFoldIdx(int foldIdx)
    {
        if ( foldIdx < 0 || foldIdx >= foldCnt )
        {
            throw new IllegalArgumentException();
        }
    }

}
