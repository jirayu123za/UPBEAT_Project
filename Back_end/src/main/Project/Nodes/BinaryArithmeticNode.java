package Project.Nodes;
import Project.GameProcess.Game;

public class BinaryArithmeticNode extends ExpressionNode{
    protected ExpressionNode left, right;
    protected String op;

    public BinaryArithmeticNode(ExpressionNode left, String op, ExpressionNode right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public long evaluate(Game bindings) throws ArithmeticException {
        long LeftValue = left.evaluate(bindings);
        long RightValue = right.evaluate(bindings);

        if(op.equals("+"))
            return LeftValue + RightValue;
        if(op.equals("-"))
            return LeftValue - RightValue;
        if(op.equals("*"))
            return LeftValue * RightValue;
        if(op.equals("/")){
            if (RightValue == 0){
                throw new ArithmeticException("Divide by zero");
            }
                return (long) Math.floor(LeftValue / RightValue);
            }
        if(op.equals("%")){
            if(RightValue == 0){
                throw new ArithmeticException("Modulo by zero");
            }
                return LeftValue % RightValue;
            }
        if(op.equals("^")){
                return (long) Math.pow(LeftValue, RightValue);
        }
        throw new NodeException.UnknownOp("Unknown op: " + op);
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", left.toString(), op, right.toString());
    }
}
