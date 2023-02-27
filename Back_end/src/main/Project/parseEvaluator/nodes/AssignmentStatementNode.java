package Project.parseEvaluator.nodes;

public class AssignmentStatementNode implements Node{
    protected VariableNode variableNode;
    protected Node expressionNode;

    public AssignmentStatementNode(VariableNode variableNode, Node expressionNode){
        this.variableNode = variableNode;
        this.expressionNode = expressionNode;
    }

    @Override
    public double evaluate() {
        variableNode.assignValue(expressionNode.evaluate());
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
