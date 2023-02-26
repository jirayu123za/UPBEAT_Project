package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class AttackCommandNode implements Node{
    protected String direction;
    protected CityCrew city;

    public AttackCommandNode(String direction, CityCrew city){
        this.direction = direction;
        this.city = city;
    }

    @Override
    public double evaluate() {
        if(!city.getDidActionCommand()){
            city.shoot(direction);
            city.setDidActionCommand(true);
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
