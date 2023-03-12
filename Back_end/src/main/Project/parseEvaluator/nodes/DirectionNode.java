package Project.parseEvaluator.nodes;
import Project.parseEvaluator.RegularExpression;
import Project.parseEvaluator.SyntaxError;
import java.util.Map;

public class DirectionNode implements Node{
    protected String direction;

    public DirectionNode(String direction) throws SyntaxError{
        if(!direction.matches(RegularExpression.DIRECTION_REGEX)){
            throw new SyntaxError("Invalid direction string: " + direction);
        }
        this.direction = direction;
    }

    @Override
    public long evaluate(Map<String, Integer> bindings){
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings){
        for (int i = 0; i < height; ++i) {
            System.out.print("   ");
        }
        System.out.println(" |---" + direction);
    }
}
