package Project.parseEvaluator.nodes;
import Project.GameProcess.Game;

public class CollectCommand extends CommandNode{
    protected ExpressionNode expressionNode;

    public CollectCommand(ExpressionNode expressionNode){
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.collect(expressionNode.evaluate(bindings));
    }
}
