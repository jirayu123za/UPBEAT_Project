package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class RegionCommandNode implements Node{
    protected Node expressionNode;

    public RegionCommandNode(Node expressionNode){
        this.expressionNode = expressionNode;
    }

    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
