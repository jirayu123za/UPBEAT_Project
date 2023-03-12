package Project.parseEvaluator.nodes;
import Project.GameProcess.Game;

public abstract class ExecuteNode implements Node {
    public ExpressionNode next;
    public abstract boolean execute(Game bindings);
}
