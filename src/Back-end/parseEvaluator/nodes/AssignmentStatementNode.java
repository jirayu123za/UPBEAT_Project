package parseEvaluator.nodes;

public class AssignmentStatementNode implements Node{
    protected VarNode varNode;
    protected Node expressionNode;

    public AssignmentStatementNode(VarNode varNode, Node expressionNode){
        this.varNode = varNode;
        this.expressionNode = expressionNode;
    }

    @Override
    public double evaluate() {
        varNode.assignValue(expressionNode.evaluate());
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
