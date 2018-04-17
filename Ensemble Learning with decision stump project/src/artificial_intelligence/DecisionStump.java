package artificial_intelligence;

import bank_to_int.int_tensor.Instance;

import java.util.Map;

/**
 * Created by nafee on 4/17/18.
 */
public class DecisionStump extends Hypothesis
{
    int attributeIdx;
    Map<Integer, Integer> map;
    public static final int DEFAULT_TYPE = 0;

    public DecisionStump(int attributeIdx, Map<Integer, Integer> map) {
        if ( attributeIdx < 0 )
        {
            throw new IllegalArgumentException();
        }

        if ( map == null )
        {
            throw new NullPointerException();
        }

        this.attributeIdx = attributeIdx;
        this.map = map;
    }

    @Override
    public int giveType(Instance instance) {
        if ( instance == null )
        {
            throw new IllegalArgumentException();
        }

        int attribute = instance.getAttribute(attributeIdx);

        if ( ! map.containsKey(attribute) ) // We have never seen this attribute while training
        {
            System.out.println(" CAUTION : Decision stump returning a default value. ");
            return DEFAULT_TYPE;
        }

        return map.get(attribute);
    }
}
