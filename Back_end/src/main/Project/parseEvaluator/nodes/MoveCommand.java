package Project.parseEvaluator.nodes;
import java.util.Map;

public class MoveCommand extends CommandNode{
    protected String move;
    protected DirectionNode direction;

    public MoveCommand(String move, DirectionNode direction){
        this.move = move;
        this.direction = direction;
    }

    @Override
    public double evaluate(Map<String, Integer> bindings){
        /*Need implement*/
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.println(" |---Move " + direction);
    }
}
