package bank_to_int.int_tensor;

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
}
