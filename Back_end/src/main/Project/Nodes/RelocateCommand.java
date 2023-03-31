package Project.Nodes;
import Project.Nodes.Node.*;
import Project.GameProcess.Game;

public class RelocateCommand extends ExecuteNode {
    public RelocateCommand(){
    }

    @Override
    public boolean execute(Game bindings) {
        bindings.relocate();
        return true;
    }
}
