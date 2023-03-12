package Project.Nodes;
import Project.GameProcess.Game;

public abstract class ExecuteNode implements Node {
    public ExpressionNode next;
    public abstract boolean execute(Game bindings);
}
