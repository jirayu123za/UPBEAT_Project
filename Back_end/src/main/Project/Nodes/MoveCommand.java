package Project.Nodes;
import Project.Nodes.Node.*;
import Project.GameProcess.Game;

public class MoveCommand extends ExecuteNode{
    protected DirectionNode direction;

    public MoveCommand(DirectionNode direction){
        this.direction = direction;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.move(direction);
    }
}
