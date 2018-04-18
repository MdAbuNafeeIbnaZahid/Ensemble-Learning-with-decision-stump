package my_util;

import bank_to_int.int_tensor.Instance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my_util.MyUtil.checkNotNull;
import static my_util.MyUtil.getMostFrequent;

/**
 * Created by nafee on 4/18/18.
 */
public class MyMapList extends MapList<Integer,Integer> {

    public MyMapList( List<Instance> instanceList, int attributeIdx )
    {
        super();
        checkNotNull(instanceList);
        for (Instance instance : instanceList)
        {
            if ( attributeIdx < 0 || attributeIdx >= instance.getAttributeCnt() )
            {
                throw new IllegalArgumentException();
            }
            int attribute = instance.getAttribute(attributeIdx);
            int label = instance.getLabel();
            add(attribute, label);
        }
    }


    public Map<Integer, Integer> getKeyToMostFreqValMap() // This is the map used by decision stump
    {
        Map<Integer, Integer> keyToMostFreqValMap = new HashMap<>();
        for ( Integer key : keySet() )
        {
            List<Integer> valueListWithKey = get(key);
            int mostFreqValWithKey = getMostFrequent(valueListWithKey);
            keyToMostFreqValMap.put(key, mostFreqValWithKey);
        }

        return keyToMostFreqValMap;
    }


    public void addBatch(List<Integer> keyList, List<Integer> valList)
    {
        checkNotNull(keyList);
        checkNotNull(valList);
        if (keyList.size() != valList.size())
        {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < keyList.size(); i++)
        {
            int key = keyList.get(i);
            int val = valList.get(i);
            add(key, val);
        }
    }

    public int getValCnt(int key)
    {
        List<Integer> valList = safeGet(key);
        return valList.size();
    }

    public int getTotalValCnt()
    {
        int ret = 0;
        for (Integer key : keySet())
        {
            ret += getValCnt(key);
        }
        return ret;
    }

    private double getEntropy(Integer key)
    {
        List<Integer> valList = safeGet(key);
        double entropy = MyUtil.getEntropy(valList);
        return entropy;
    }

    public double getTotalEntropy()
    {
        double totalEntropy = 0;

        double totalValCnt = getTotalValCnt();
        for (Integer key : keySet())
        {
            double bucketEntropy = getEntropy(key);
            double curBucketWeight = (1.0 * getValCnt(key) ) / totalValCnt;
            double entropyAddee = curBucketWeight * bucketEntropy;

            totalEntropy += entropyAddee;
        }

        return totalEntropy;
    }



}
