package Project.parseEvaluator.nodes;
import Project.CityCrew;

public class DoneCommandNode implements Node{
    protected CityCrew city;

    public DoneCommandNode(CityCrew city){
            this.city = city;
    }

    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
