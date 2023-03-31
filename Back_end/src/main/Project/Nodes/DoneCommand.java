package Project.Nodes;
import Project.GameProcess.Game;
import Project.Nodes.Node.*;

public class DoneCommand extends ExecuteNode {
    public DoneCommand(){
    }

    @Override
    public boolean execute(Game bindings) {
        return false;
    }
}
