package Project.parseEvaluator.nodes;
import Project.parseEvaluator.RegularExpression;
import Project.parseEvaluator.SyntaxError;

import java.util.Map;

public class AttackCommand implements Node{
    protected String action;
    protected Node expressionNode;
    protected DirectionNode direction;

    public AttackCommand(String action, DirectionNode direction, Node expressionNode) throws SyntaxError {
        if(!action.matches(RegularExpression.SHOOT_REGEX)){
            throw new SyntaxError("Invalid action string: " + action);
        }
        this.action = action;
        this.direction = direction;
        this.expressionNode = expressionNode;
    }

    @Override
    public double evaluate(Map<String, Integer> bindings) {
        // need implement shoot game action
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.println(" |---Shoot " + direction);
    }
}
