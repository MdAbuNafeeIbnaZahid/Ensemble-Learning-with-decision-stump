package artificial_intelligence;

import bank_to_int.int_tensor.Instance;
import my_util.MyMapList;

import java.util.List;
import java.util.Map;
import static my_util.MyUtil.checkNotNull;
/**
 * Created by nafee on 4/17/18.
 */
public class DecisionStump extends Hypothesis
{
    int attributeIdx;
    Map<Integer, Integer> map;
    public static final int DEFAULT_TYPE = 0;

    public DecisionStump(List<Instance> instanceList, int attributeIdx)
    {
        if ( attributeIdx < 0 )
        {
            throw new IllegalArgumentException();
        }

        checkNotNull(instanceList);

        MyMapList myMapList = new MyMapList(instanceList, attributeIdx);
        Map keyToMostFreqValMap = myMapList.getKeyToMostFreqValMap();

        this.attributeIdx = attributeIdx;
        this.map = keyToMostFreqValMap;
    }


    private DecisionStump(int attributeIdx, Map<Integer, Integer> map) {


        checkNotNull(map);

        this.attributeIdx = attributeIdx;
        this.map = map;
    }

    @Override
    public int giveLabel(Instance instance) {
        checkNotNull(instance);

        int attribute = instance.getAttribute(attributeIdx);

        if ( ! map.containsKey(attribute) ) // We have never seen this attribute while training
        {
            System.out.println(" CAUTION : Decision stump returning a default value. ");
            return DEFAULT_TYPE;
        }

        return map.get(attribute);
    }


}
