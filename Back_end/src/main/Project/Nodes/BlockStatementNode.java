package Project.Nodes;
import Project.GameProcess.Game;

import java.util.*;

public class BlockStatementNode extends ExecuteNode {
    public LinkedList<ExecuteNode> statements;

    public BlockStatementNode(LinkedList<ExecuteNode> statements){
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
