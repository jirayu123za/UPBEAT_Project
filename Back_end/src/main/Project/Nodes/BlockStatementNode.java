package Project.Nodes;
import Project.GameProcess.Game;

import java.util.*;

public class BlockStatementNode extends ExecuteNode {
    public List<ExecuteNode> statements;

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
