package Project.parseEvaluator.nodes;
import java.util.Map;

public class NumberNode implements Node{
    protected long value;

    public NumberNode(long value){
        this.value = value;
    }

    @Override
    public long evaluate(Map<String, Integer> bindings){
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
