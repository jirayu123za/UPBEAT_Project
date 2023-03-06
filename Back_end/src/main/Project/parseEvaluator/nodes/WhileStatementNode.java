package Project.parseEvaluator.nodes;
import java.util.*;

public class WhileStatementNode implements Node{
    Node expressionNode;
    Node statementNode;

    public WhileStatementNode(Node expressionNode, Node statementNode) {
        if(expressionNode == null){
            throw new IllegalArgumentException("Null == expressionNode");
        }
        this.expressionNode = expressionNode;
        this.statementNode = statementNode;
    }

    @Override
    public double evaluate(Map<String, Integer> bindings) {
        for (int counter = 0; counter < 10000 && expressionNode.evaluate(bindings) > 0; counter++){
            statementNode.evaluate(bindings);
        }
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.print(" |---While ");
        expressionNode.print(0, bindings);
        statementNode.print(height+1, bindings);
    }
}
