package bank_to_int.int_tensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nafee on 4/17/18.
 */
public class InstanceWithWeight
{
    private Instance instance;
    private double weight;

    public InstanceWithWeight(Instance instance, double weight) {
        this.instance = instance;
        this.weight = weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void multipleyWeight(double multiplier)
    {
        weight *= multiplier;
    }

    public Instance getInstance() {
        return instance;
    }

    public static List<InstanceWithWeight> getInstanceWithWeightList( List<Instance> instanceList )
    {
        if ( instanceList == null )
        {
            throw new NullPointerException();
        }

        List<InstanceWithWeight> instanceWithWeightList = new ArrayList<>();
        double weight = 1.0 / instanceList.size() ;
        for ( Instance instance : instanceList )
        {
            InstanceWithWeight instanceWithWeight = new InstanceWithWeight(instance, weight);
            instanceWithWeightList.add( instanceWithWeight );
        }

        return instanceWithWeightList;
    }
}
