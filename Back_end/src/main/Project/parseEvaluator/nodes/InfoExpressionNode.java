package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class InfoExpressionNode implements Node{
    protected String type;
    protected CityCrew city;

    public InfoExpressionNode(String type, CityCrew city){
        this.type = type;
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
