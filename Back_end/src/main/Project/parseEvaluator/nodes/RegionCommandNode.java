package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class RegionCommandNode implements Node{
    protected Node expressionNode;
    protected boolean isInvestCommand;
    protected boolean isCollectCommand;
    protected CityCrew city;

    public RegionCommandNode(Node expressionNode, boolean isInvestCommand, boolean isCollectCommand, CityCrew city){
        this.expressionNode = expressionNode;
        this.isInvestCommand = isInvestCommand;
        this.isCollectCommand = isCollectCommand;
        this.city = city;
    }

    @Override
    public double evaluate() {
        double deposit = expressionNode.evaluate();
        if(isCollectCommand){
            city.collectDeposit((long) deposit);
        }else if(isInvestCommand){
            city.investDeposit((long) deposit);
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
