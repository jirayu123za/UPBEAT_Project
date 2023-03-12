package Project.parseEvaluator.nodes;
import Project.GameProcess.Game;

public class AttackCommand extends CommandNode{
    protected ExpressionNode expressionNode;
    protected DirectionNode direction;

    public AttackCommand(DirectionNode direction, ExpressionNode expressionNode){
        this.direction = direction;
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.attack(direction, expressionNode.evaluate(bindings));
    }
}
