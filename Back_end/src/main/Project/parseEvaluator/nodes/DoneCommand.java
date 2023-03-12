package Project.parseEvaluator.nodes;
import Project.GameProcess.Game;

public class DoneCommand extends CommandNode{
    public DoneCommand(){
    }

    @Override
    public boolean execute(Game bindings) {
        return false;
    }
}
