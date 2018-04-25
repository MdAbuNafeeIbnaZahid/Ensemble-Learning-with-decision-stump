package selecting_probabilistically;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by nafee on 11/23/17.
 */
public class ProbabilisticSelector {

    private List<ItemWithProbability> itemWithProbabilityList;
    private Double succcessiveSumOfRelativeProbability[];

    private double relativeProbabilitySum = 0;
    private Random random = new Random();

    public ProbabilisticSelector(List<ItemWithProbability> itemWithProbabilityList) {

        if ( itemWithProbabilityList == null )
        {
            throw new NullPointerException();
        }

        this.itemWithProbabilityList = itemWithProbabilityList;
        succcessiveSumOfRelativeProbability = new Double[itemWithProbabilityList.size()];
//        succcessiveSumOfRelativeProbability = itemWithProbabilityList.toArray(new Double[itemWithProbabilityList.size()]);

        if ( itemWithProbabilityList.isEmpty() )
        {
            return;
        }

        double firstRelativeProbability = itemWithProbabilityList.get(0).getRelativeProbability();
        relativeProbabilitySum = firstRelativeProbability;
        succcessiveSumOfRelativeProbability[0] = firstRelativeProbability;

        for (int i = 1; i < itemWithProbabilityList.size(); i++ )
        {
            ItemWithProbability itemWithProbability = itemWithProbabilityList.get(i);
            double relativeProbability = itemWithProbability.getRelativeProbability();
            relativeProbabilitySum += relativeProbability;
            succcessiveSumOfRelativeProbability[i] = succcessiveSumOfRelativeProbability[i-1] + relativeProbability;
        }


    }



    // Till now the getItemProbabilistically() is a O(n) runtime method
    public Object getItemProbabilistically()
    {
        if ( itemWithProbabilityList.isEmpty() ) // Given an empty iterable
        {
            throw new Error(" itemWithProbabilityList.isEmpty() ");
        }

        double randProbSum = random.nextDouble()*relativeProbabilitySum;
        double probSum = 0;
        int idx = 0;

        while (probSum < randProbSum)
        {
            probSum += itemWithProbabilityList.get(idx++).getRelativeProbability();
        }

        int selectedIdx = Math.max(0, idx-1);
        ItemWithProbability selectedItemWithProb = itemWithProbabilityList.get(selectedIdx);
        return selectedItemWithProb.getItem();

    }

    public List<Object> getItemsProbabilistically( int itemCount )
    {
        if ( itemWithProbabilityList.isEmpty() )
        {
            throw new Error(" itemWithProbabilityList.isEmpty() ");
        }

        List<Object> objectList = new ArrayList<>();
        for (int i = 1; i <= itemCount; i++)
        {
            Object object = getItemProbabilistically();
            objectList.add(object);
        }

        return objectList;
    }
}
