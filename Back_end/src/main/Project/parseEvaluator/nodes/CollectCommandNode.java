package Project.parseEvaluator.nodes;

public class CollectCommandNode implements Node{
    protected Node expressionNode;

    public CollectCommandNode(Node expressionNode){
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
