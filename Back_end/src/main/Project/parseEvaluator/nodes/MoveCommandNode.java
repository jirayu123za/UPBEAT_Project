package Project.parseEvaluator.nodes;

public class MoveCommandNode implements Node{
    protected String direction;

    public MoveCommandNode(String direction){
        this.direction = direction;

    }

    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
