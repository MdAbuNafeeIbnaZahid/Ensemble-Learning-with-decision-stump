package artificial_intelligence;

import bank_to_int.int_tensor.Instance;
import my_util.MyUtil;

import java.util.List;

/**
 * Created by nafee on 4/17/18.
 */
public class DecisionStumpLearning extends LearningAlgorithm {


    public Hypothesis learn(List<Instance> instanceList, double[] weightAr)
    {
        if ( instanceList == null )
        {
            throw new NullPointerException();
        }

        if ( weightAr == null )
        {
            throw new NullPointerException();
        }

        if ( instanceList.size() != weightAr.length )
        {
            throw new IllegalArgumentException(" instanceList.size() != weightAr.length ");
        }

        MyUtil.normalize(weightAr);

        return null;
    }

}
