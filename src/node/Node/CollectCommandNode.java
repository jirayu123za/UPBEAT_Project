public class CollectCommandNode implements Node{
    protected Node expression;
    protected CityCrew city;

    public CollectCommandNode(Node expr, CityCrew city){
        this.expression = expr;
        this.city = city;
    }

    @Override
    public double evaluate() {
        long deposit = (long) expression.evaluate();
        city.collectDeposit(deposit);
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
