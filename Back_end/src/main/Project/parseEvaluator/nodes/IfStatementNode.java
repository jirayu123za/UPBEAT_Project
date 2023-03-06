package Project.parseEvaluator.nodes;
import java.util.Map;

public class IfStatementNode implements Node{
    protected Node expressionNode;
    protected Node ifTrueStatementNode;
    protected Node ifFalseStatementNode;

    public IfStatementNode(Node expressionNode, Node ifTrueStatementNode, Node ifFalseStatementNode) {
        this.expressionNode = expressionNode;
        this.ifTrueStatementNode = ifTrueStatementNode;
        this.ifFalseStatementNode = ifFalseStatementNode;
    }

    @Override
    public double evaluate(Map<String, Integer> bindings) {
        if(expressionNode.evaluate(bindings) > 0){
            ifTrueStatementNode.evaluate(bindings);
        }else{
            ifFalseStatementNode.evaluate(bindings);
        }
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.print(" |---If ");
        expressionNode.print(0, bindings);
        System.out.println();
        ifTrueStatementNode.print(height+1, bindings);
        ifFalseStatementNode.print(height+1, bindings);
    }
}
