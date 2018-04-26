package artificial_intelligence;

/**
 * Created by nafee on 4/19/18.
 */

import bank_to_int.int_tensor.Instance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static my_util.MyUtil.checkNotNull;

public class WeightedMajority extends Hypothesis{
    Hypothesis[] hypothesisArray;
    double[] weightAr;

    public WeightedMajority(Hypothesis[] hypothesisArray, double[] weightAr)
    {
        checkNotNull(hypothesisArray);
        checkNotNull(weightAr);

        assert hypothesisArray.length == weightAr.length;

        for ( Hypothesis hypothesis : hypothesisArray )
        {
            checkNotNull(hypothesis);
        }

        this.hypothesisArray = hypothesisArray;
        this.weightAr = weightAr;
    }

    public int giveLabel(Instance instance)
    {
        Map<Integer, Double> typeToWeightMap = new HashMap<>();

        for (int i = 0; i < hypothesisArray.length; i++)
        {
            Hypothesis hypothesis = hypothesisArray[i];
            double weight = weightAr[i];

            int type = hypothesis.giveLabel(instance);

            typeToWeightMap.merge(type, weight, Double::sum);
        }

        int type = Collections.max(typeToWeightMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        return type;
    }
}
