package Project.parseEvaluator.nodes;
import java.util.*;

public class AssignmentStatementNode implements Node{
    protected VariableExpressionNode variableExpressionNode;
    protected Node expressionNode;

    public AssignmentStatementNode(VariableExpressionNode variableExpressionNode, Node expressionNode){
        this.variableExpressionNode = variableExpressionNode;
        this.expressionNode = expressionNode;
    }

    @Override
    public long evaluate(Map<String, Integer> bindings) {
        variableExpressionNode.assignValue(expressionNode.evaluate(bindings));
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.print(" |---Assign ");
        variableExpressionNode.print(0,bindings);
        System.out.print(" = ");
        expressionNode.print(0,bindings);
        System.out.println();
    }
}
