public class WhileStatementNode implements Node{
    Node exprNode;
    Node statementNode;

    public WhileStatementNode(Node exprNode, Node statementNode) {
        this.exprNode = exprNode;
        this.statementNode = statementNode;
    }

    @Override
    public double evaluate() {
        for (int counter = 0; counter < 10000 && exprNode.evaluate() > 0; counter++){
            statementNode.evaluate();
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
