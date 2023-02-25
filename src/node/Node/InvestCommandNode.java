public class InvestCommandNode implements Node{
    protected Node expressionNode;
    protected CityCrew city;

    public InvestCommandNode(Node expr, CityCrew city){
        this.expressionNode = expr;
        this.city = city;
    }

    @Override
    public double evaluate() {
        long budget = city.getBudget();
        long deposit = (long) expressionNode.evaluate();
        if(budget >= deposit){
            city.investDeposit(deposit);
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
