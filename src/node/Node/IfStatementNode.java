public class IfStatementNode implements Node{
    Node exprNode;
    Node ifTrueStatementNode;
    Node ifFalseStatementNode;

    public IfStatementNode(Node exprNode, Node ifTrueStatementNode, Node ifFalseStatementNode) {
        this.exprNode = exprNode;
        this.ifTrueStatementNode = ifTrueStatementNode;
        this.ifFalseStatementNode = ifFalseStatementNode;
    }

    @Override
    public double evaluate() {
        if(exprNode.evaluate() > 0){
            ifTrueStatementNode.evaluate();
        }else{
            ifFalseStatementNode.evaluate();
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
