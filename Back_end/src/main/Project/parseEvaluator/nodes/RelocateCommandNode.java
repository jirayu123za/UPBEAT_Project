package Project.parseEvaluator.nodes;

public class RelocateCommandNode implements Node{
    protected String direction;

    public RelocateCommandNode(String direction){
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
