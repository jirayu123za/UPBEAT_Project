package Project.Nodes;
import Project.GameProcess.Game;
import Project.Nodes.Node.*;

import java.util.*;

public class BlockStatementNode extends ExecuteNode {
    protected List<ExecuteNode> statements;

    public BlockStatementNode(List<ExecuteNode> statements){
        this.statements = statements;
    }

    @Override
    public boolean execute(Game bindings) {
        for(ExecuteNode statement : statements){
            if(!statement.execute(bindings)){
                return false;
            }
        }
        return true;
    }
}
