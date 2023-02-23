public class UnaryArithmeticNode implements Node{
    protected String operator;
    protected Node expression;

    public UnaryArithmeticNode(String op, Node expr){
        this.operator = op;
        this.expression = expr;
    }

    @Override
    public double evaluate() {
        double value = expression.evaluate();
        if(operator.equals("-")) {
            return -value;
        }
        return value;
    }

    @Override
    public void print(int height) {

    }
}
