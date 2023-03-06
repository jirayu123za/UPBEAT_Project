package Project.parseEvaluator.nodes;
import java.util.Map;

public class RegionCommand extends CommandNode{
    protected String action;
    protected Node expressionNode;

    public RegionCommand(String action, Node expressionNode){
        this.action = action;
        this.expressionNode = expressionNode;
    }

    @Override
    public double evaluate(Map<String, Integer> bindings) {
        /*Need implement*/
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.println(" |---Region " + expressionNode);
    }
}
