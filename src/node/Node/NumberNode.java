public class NumberNode implements Node{
    protected double number;

    public NumberNode(double number){
        this.number = number;
    }

    @Override
    public double evaluate() {
        return number;
    }

    @Override
    public void print(int height) {

    }
}
