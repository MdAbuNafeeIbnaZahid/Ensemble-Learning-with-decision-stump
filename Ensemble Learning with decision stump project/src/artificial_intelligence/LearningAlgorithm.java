package artificial_intelligence;

import bank_to_int.int_tensor.Instance;

import java.util.List;

/**
 * Created by nafee on 4/17/18.
 */
public abstract class LearningAlgorithm {
    public abstract Hypothesis learn(List<Instance> instanceList, double[] weightAr);
}
