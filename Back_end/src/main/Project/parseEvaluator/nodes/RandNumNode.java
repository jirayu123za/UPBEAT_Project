package Project.parseEvaluator.nodes;
import java.util.*;

public class RandNumNode extends VariableNode {
    protected Random rand;

    public RandNumNode(){
        rand = new Random();
        identifier = "random";
    }

    @Override
    public double evaluate(){
        return rand.nextInt()%1000;
    }

    @Override
    public void assignValue(double value){
        // no-op
    }

}
