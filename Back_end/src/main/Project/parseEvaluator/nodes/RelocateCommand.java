package Project.parseEvaluator.nodes;

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
