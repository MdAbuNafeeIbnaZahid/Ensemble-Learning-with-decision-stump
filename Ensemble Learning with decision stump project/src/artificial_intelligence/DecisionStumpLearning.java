package artificial_intelligence;

import bank_to_int.int_tensor.Instance;
import my_util.MyMapList;
import my_util.MyUtil;
import selecting_probabilistically.ItemWithProbability;
import selecting_probabilistically.ProbabilisticSelector;

import java.util.ArrayList;
import java.util.List;

import static  my_util.MyUtil.checkNotNull;

/**
 * Created by nafee on 4/17/18.
 */
public class DecisionStumpLearning extends LearningAlgorithm {

    public final int RESAMPLED_TRAINING_SET_SIZE = 1000;

    public Hypothesis learn(List<Instance> instanceList, double[] weightAr)
    {
        checkNotNull(instanceList);
        checkNotNull(weightAr);

        if ( instanceList.size() != weightAr.length )
        {
            throw new IllegalArgumentException(" instanceList.size() != weightAr.length ");
        }

        MyUtil.normalize(weightAr);

        List<Instance> resampledInstanceList = getResampledInstanceList(instanceList, weightAr);
        assert resampledInstanceList.size() == RESAMPLED_TRAINING_SET_SIZE;


        DecisionStump decisionStump = getDecisionStumpFromInstances(resampledInstanceList);

        return decisionStump;
    }

    private List<Instance> getResampledInstanceList(List<Instance> instanceList, double[] weightAr)
    {
        List<Instance> resampledInstanceList = new ArrayList<>();
        List<ItemWithProbability> itemWithProbabilityList = getItemWithProbabilityList(instanceList, weightAr);
        ProbabilisticSelector probabilisticSelector = new ProbabilisticSelector(itemWithProbabilityList);
        List<Object> objectList = probabilisticSelector.getItemsProbabilistically(RESAMPLED_TRAINING_SET_SIZE);

        for (Object object : objectList)
        {
            Instance resampledInstance = (Instance) object;
            resampledInstanceList.add(resampledInstance);
        }

        return resampledInstanceList;
    }

    private List<ItemWithProbability> getItemWithProbabilityList(List<Instance> instanceList, double[] weightAr )
    {
        List<ItemWithProbability> itemWithProbabilityList = new ArrayList<>();
        for ( int i = 0; i < weightAr.length; i++)
        {
            Instance instance = instanceList.get(i);
            double weight = weightAr[i];
            ItemWithProbability itemWithProbability = new ItemWithProbability(weight, instance);
            itemWithProbabilityList.add(itemWithProbability);
        }
        return itemWithProbabilityList;
    }

    private DecisionStump getDecisionStumpFromInstances(List<Instance> instanceList)
    {
        checkNotNull(instanceList);
        if ( instanceList.isEmpty() )
        {
            throw new IllegalArgumentException();
        }
        Instance.getCommonAttributeCnt( instanceList );

        int bestAttributeIdx = getBestAttributeIdxForDecisionStump(instanceList);
        return new DecisionStump(instanceList, bestAttributeIdx);
    }

    private int getBestAttributeIdxForDecisionStump(List<Instance> instanceList)
    {
        checkNotNull(instanceList);
        if ( instanceList.isEmpty() )
        {
            throw new IllegalArgumentException();
        }

        int commonAttributeCnt = Instance.getCommonAttributeCnt(instanceList);
        assert commonAttributeCnt > 0;

        // Let's first initialize our values for the decision stump considering the first attribute
        int bestAttributeIdxForDecisionStump = 0;
        double lowestOneLevelDeepEntropy = getOneLevelDeepEntropy(instanceList, 0);

        for (int attributeIdx = 0; attributeIdx < commonAttributeCnt; attributeIdx++)
        {
            double oneLevelDeepEntropy = getOneLevelDeepEntropy(instanceList, attributeIdx);
            if ( oneLevelDeepEntropy < lowestOneLevelDeepEntropy )
            {
                lowestOneLevelDeepEntropy = oneLevelDeepEntropy;
                bestAttributeIdxForDecisionStump = attributeIdx;
            }
        }

        return bestAttributeIdxForDecisionStump;
    }

    private double getOneLevelDeepEntropy( List<Instance> instanceList, int attribute )
    {
        checkNotNull(instanceList);
        if ( instanceList.isEmpty() )
        {
            throw new IllegalArgumentException();
        }

        int commonAttributeCnt = Instance.getCommonAttributeCnt(instanceList);

        if ( attribute < 0 || attribute >= commonAttributeCnt )
        {
            throw new IllegalArgumentException();
        }

        MyMapList myMapList = new MyMapList(instanceList, attribute);
        double oneLevelDeepEntropy = myMapList.getTotalEntropy();

        return oneLevelDeepEntropy;
    }




}
