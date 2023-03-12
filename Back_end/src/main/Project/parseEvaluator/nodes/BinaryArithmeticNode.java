package Project.parseEvaluator.nodes;
import java.util.Map;

public class BinaryArithmeticNode implements Node{
    protected Node left, right;
    protected String op;

    public BinaryArithmeticNode(Node left, String op, Node right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public long evaluate(Map<String, Integer> bindings) throws ArithmeticException {
        if(bindings == null) {
            throw new ArithmeticException("bindings == null");
        }
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
        throw new ArithmeticException("Unknown op: " + op);
    }

    @Override
    public void print(int height, Map<String,Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        left.print(0, bindings);
        System.out.print(" " + op + " ");
        right.print(0, bindings);
    }
}
