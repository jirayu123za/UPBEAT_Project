package Project.Nodes;

import Project.GameProcess.Game;

public class MoveCommand extends CommandNode{
    protected DirectionNode direction;

    public MoveCommand(DirectionNode direction){
        this.direction = direction;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.move(direction);
    }
}
