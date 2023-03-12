package Project.Nodes;

import Project.GameProcess.Game;

public class InvestCommand extends CommandNode{
    protected ExpressionNode expressionNode;

    public InvestCommand(ExpressionNode expressionNode){
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.invest(expressionNode.evaluate(bindings));
    }
}
