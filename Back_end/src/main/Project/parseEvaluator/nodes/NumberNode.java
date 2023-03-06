package Project.parseEvaluator.nodes;
import java.util.Map;

public class NumberNode implements Node{
    protected double value;

    public NumberNode(double value){
        this.value = value;
    }

    @Override
    public double evaluate(Map<String, Integer> bindings){
        if(bindings == null){
            throw new IllegalArgumentException("bindings == null");
        }
        return value;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.print(value);
    }
}
