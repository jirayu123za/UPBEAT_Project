package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class InfoExpressionNode implements Node{
    protected String type;
    protected String direction;
    protected CityCrew city;

    public InfoExpressionNode(String type, String direction, CityCrew city){
        this.type = type;
        this.direction = direction;
        this.city = city;
    }

    @Override
    public double evaluate() {
        return city.getOpponentLocation();
    }

    @Override
    public void print(int height) {

    }
}
