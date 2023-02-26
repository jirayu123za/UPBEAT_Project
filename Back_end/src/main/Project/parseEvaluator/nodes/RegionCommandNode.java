package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class RegionCommandNode implements Node{
    protected Node expressionNode;
    protected CityCrew city;

    public RegionCommandNode(Node expressionNode, CityCrew city){
        this.expressionNode = expressionNode;
        this.city = city;
    }

    @Override
    public double evaluate() {
        double deposit = expressionNode.evaluate();
        if(true){
            city.collectDeposit((long) deposit);
        }else if(true){
            city.investDeposit((long) deposit);
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
