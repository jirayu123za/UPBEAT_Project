package Project.parseEvaluator.nodes;

public class InfoExpressionNode implements Node{
    protected String type;
    protected String direction;

    public InfoExpressionNode(String type, String direction){
        this.type = type;
        this.direction = direction;
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
        System.out.print(" type " + type + " direction " + direction);
    }
}
