package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class CollectCommandNode implements Node{
    protected Node expressionNode;
    protected CityCrew city;

    public CollectCommandNode(Node expressionNode, CityCrew city){
        this.expressionNode = expressionNode;
        this.city = city;
    }

    @Override
    public double evaluate() {
        long deposit = (long) expressionNode.evaluate();
        city.collectDeposit(deposit);
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
