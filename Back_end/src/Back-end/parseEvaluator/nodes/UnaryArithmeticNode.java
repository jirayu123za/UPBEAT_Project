package parseEvaluator.nodes;

public class UnaryArithmeticNode implements Node{
    protected String operator;
    protected Node expressionNode;

    public UnaryArithmeticNode(String op, Node expressionNode){
        this.operator = op;
        this.expressionNode = expressionNode;
    }

    @Override
    public double evaluate() {
        double value = expressionNode.evaluate();
        if(operator.equals("-")) {
            return -value;
        }
        return value;
    }

    @Override
    public void print(int height) {

    }
}
