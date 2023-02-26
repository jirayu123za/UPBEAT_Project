package Project.parseEvaluator.nodes;

public class BinaryArithmeticNode implements Node{
    protected Node left, right;
    protected String op;

    public BinaryArithmeticNode(Node left, String op, Node right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public double evaluate() throws ArithmeticException{
        double LeftValue = left.evaluate();
        double RightValue = right.evaluate();

        if (op.equals("+"))
            return LeftValue + RightValue;
        if (op.equals("-"))
            return LeftValue - RightValue;
        if (op.equals("*"))
            return LeftValue * RightValue;
        if (op.equals("/")){
            if(RightValue == 0){
                throw new ArithmeticException("Divide by zero");
            }
            return Math.floor(LeftValue / RightValue);
        }
        if (op.equals("%")){
            if(RightValue == 0){
                throw new ArithmeticException("Modulo by zero");
            }
            return LeftValue % RightValue;
        }
        if (op.equals("^")){
            return Math.pow(LeftValue, RightValue);
        }
        throw new ArithmeticException("Unknown op: " + op);
    }

    @Override
    public void print(int height) {

    }
}
