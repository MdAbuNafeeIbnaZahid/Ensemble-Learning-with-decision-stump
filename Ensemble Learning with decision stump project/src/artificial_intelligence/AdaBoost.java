package artificial_intelligence;

import bank_to_int.int_tensor.Instance;

import java.util.List;

import static my_util.MyUtil.checkNotNull;
import static my_util.MyUtil.normalize;

/**
 * Created by nafee on 4/17/18.
 */
public class AdaBoost {
    public WeightedMajority adaBoost(List<Instance> instanceList, LearningAlgorithm learningAlgorithm, int boostingRoundCnt)
    {
        checkNotNull(instanceList);
        checkNotNull(learningAlgorithm);

        int instanceCnt = instanceList.size();

        double w[] = getInitWAr(instanceCnt);

        Hypothesis h[] = new Hypothesis[boostingRoundCnt];
        double z[] = new double[boostingRoundCnt];

        for (int k = 0; k < boostingRoundCnt; k++)
        {
            Hypothesis hypothesis = learningAlgorithm.learn(instanceList, w);
            h[k] = hypothesis;
            double error = 0;
            for (int j = 0; j < instanceCnt; j++)
            {
                Instance instance = instanceList.get(j);
                if ( hypothesis.giveLabel( instance ) != instance.getLabel() )
                {
                    error += w[j];
                }
            }

            if ( error >= 0.5 )
            {
                k--;
                continue;
            }

            for (int j = 0; j < instanceCnt; j++)
            {
                Instance instance = instanceList.get(j);
                if ( hypothesis.giveLabel(instance) == instance.getLabel() )
                {
                    w[j] *= error / (1-error);
                }
            }

            System.out.println(error);

            normalize(w);
            z[k] = Math.log(  (1-error) / error );
        }

        WeightedMajority weightedMajority = new WeightedMajority(h, z);
        return weightedMajority;
    }


    private double[] getInitWAr(int size)
    {
        assert size > 0;
        double[] ret = new double[size];
        for (int i = 0; i < size; i++ )
        {
            ret[i] = 1.0 / size;
        }
        return ret;
    }
}
