package Project.parseEvaluator.nodes;

public class InvestCommandNode implements Node{
    protected Node expressionNode;

    public InvestCommandNode(Node expressionNode){
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
