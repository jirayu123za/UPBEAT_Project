package Project.parseEvaluator.nodes;
import Project.GameProcess.Game;

public abstract class ExpressionNode implements Node{
    public abstract long evaluate(Game bindings);
    public abstract String toString();
}
