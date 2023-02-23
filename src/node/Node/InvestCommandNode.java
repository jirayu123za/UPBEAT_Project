public class InvestCommandNode implements Node{
    protected Node expression;
    protected CityCrew city;

    public InvestCommandNode(Node expr, CityCrew city){
        this.expression = expr;
        this.city = city;
    }

    @Override
    public double evaluate() {
        long budget = city.getBudget();
        long deposit = (long) expression.evaluate();
        if(budget >= deposit){
            city.investDeposit(deposit);
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
