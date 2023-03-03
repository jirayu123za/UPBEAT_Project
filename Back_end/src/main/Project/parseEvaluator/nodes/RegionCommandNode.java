package Project.parseEvaluator.nodes;

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
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.println(" |---Region " + expressionNode);
    }
}
