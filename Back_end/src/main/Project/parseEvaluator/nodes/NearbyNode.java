package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class NearbyNode implements Node{
    protected String direction;
    protected CityCrew city;

    public NearbyNode(String direction, CityCrew city){
        this.direction = direction;
        this.city = city;
    }

    @Override
    public double evaluate() {
        return city.getNearbyDeposit(direction);
    }

    @Override
    public void print(int height) {

    }
}
