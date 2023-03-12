package Project.Nodes;

import Project.GameProcess.Game;

public class RelocateCommand extends CommandNode{
    public RelocateCommand(){
    }

    @Override
    public boolean execute(Game bindings) {
        bindings.relocate();
        return true;
    }
}
