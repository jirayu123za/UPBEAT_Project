package Project.parseEvaluator.nodes;
import java.util.*;

public class InfoExpressionNode implements Node{
    protected String type;
    protected DirectionNode direction;

    public InfoExpressionNode(String type, DirectionNode direction){
        this.type = type;
        this.direction = direction;
    }

    @Override
    public long evaluate(Map<String, Integer> bindings) {
        if(type.equals("opponent")){
            // need implement
        }else{
            // need implement
        }
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.print(" type " + type + " direction " + direction);
    }
}
