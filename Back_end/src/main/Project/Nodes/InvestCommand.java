package Project.Nodes;
import Project.GameProcess.Game;
import Project.Nodes.Node.*;

public class InvestCommand extends ExecuteNode {
    protected ExpressionNode expressionNode;

    public InvestCommand(ExpressionNode expressionNode){
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.invest(expressionNode.evaluate(bindings));
    }
}
