package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class MoveCommandNode implements Node{
    protected String direction;
    protected CityCrew city;

    public MoveCommandNode(String direction, CityCrew city){
        this.direction = direction;
        this.city = city;
    }

    @Override
    public double evaluate() {
        if(!city.getDidActionCommand()){
            city.move(direction);
            city.setDidActionCommand(true);
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
