package Project.parseEvaluator.nodes;

public class AttackCommandNode implements Node{
    protected String direction;
    protected Node expressionNode;

    public AttackCommandNode(String direction, Node expressionNode){
        this.direction = direction;
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
