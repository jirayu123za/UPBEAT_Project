package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class OpponentNode implements Node{
    protected CityCrew city;

    public OpponentNode(CityCrew city){
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
