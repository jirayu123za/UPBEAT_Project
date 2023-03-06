package Project.parseEvaluator.nodes;
import Project.parseEvaluator.RegularExpression;
import java.util.*;

public class ActionCommand extends CommandNode{
    protected String action;
    protected Node expressionNode;
    protected DirectionNode direction;

    public ActionCommand(String action){
        this.action = action;
    }

    @Override
    public double evaluate(Map<String, Integer> bindings) {
        if(action.equals(RegularExpression.DONE_REGEX)){
            // Need Done in game action
            System.out.println(RegularExpression.DONE_REGEX);
        }else if(action.equals(RegularExpression.RELOCATE_REGEX)){
            // Need Relocate in game action
            System.out.println(RegularExpression.RELOCATE_REGEX);

        }else if(action.equals(RegularExpression.MOVE_REGEX)){
            // Need game action
            System.out.println(RegularExpression.MOVE_REGEX + " " + direction);

        }else if(action.equals(RegularExpression.INVEST_REGEX)){
            double value = expressionNode.evaluate(bindings);
            // Need game action
            System.out.println(RegularExpression.INVEST_REGEX + " " + value);

        }else if(action.equals(RegularExpression.COLLECT_REGEX)){
            double value = expressionNode.evaluate(bindings);
            // Need game action
            System.out.println(RegularExpression.COLLECT_REGEX + " " + value);

        }else if(action.equals(RegularExpression.SHOOT_REGEX)){
            double value = expressionNode.evaluate(bindings);
            // Need game action
            System.out.println(RegularExpression.SHOOT_REGEX + " " + direction + " " + value);
        }
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for (int i = 0; i < height; ++i) {
            System.out.print("   ");
        }
        System.out.println(" |---Action " + action);
    }
}
