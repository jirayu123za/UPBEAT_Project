package Project.parseEvaluator.nodes;

public class NumberNode implements Node{
    protected double value;

    public NumberNode(double value){
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public void print(int height) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.print(value);
    }
}
