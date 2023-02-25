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

    }
}
