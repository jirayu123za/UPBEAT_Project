package Project.parseEvaluator.nodes;
import java.util.*;

public class RandNumExpressionNode extends VariableExpressionNode {
    protected Random rand;

    public RandNumExpressionNode(){
        rand = new Random();
        identifier = "random";
    }

    @Override
    public long evaluate(Map<String, Integer> bindings){
        return rand.nextInt()%1000;
    }

    @Override
    public void assignValue(long value){
        // no-op
    }
}
