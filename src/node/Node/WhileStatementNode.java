public class WhileStatementNode implements Node{
    Node expressionNode;
    Node statementNode;

    public WhileStatementNode(Node expressionNode, Node statementNode) {
        this.expressionNode = expressionNode;
        this.statementNode = statementNode;
    }

    @Override
    public double evaluate() {
        for (int counter = 0; counter < 10000 && expressionNode.evaluate() > 0; counter++){
            statementNode.evaluate();
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
