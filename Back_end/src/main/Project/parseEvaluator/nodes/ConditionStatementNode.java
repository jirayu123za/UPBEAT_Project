package Project.parseEvaluator.nodes;
import Project.GameProcess.Game;

public class ConditionStatementNode extends ExecuteNode{
    protected ExpressionNode conditionNode;
    protected ExecuteNode trueStatement;
    protected ExecuteNode falseStatement;

    public ConditionStatementNode(ExpressionNode conditionNode, ExecuteNode trueStatement, ExecuteNode falseStatement){
        this.conditionNode = conditionNode;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }

    @Override
    public boolean execute(Game bindings) {
        trueStatement.next = next;
        falseStatement.next = next;

        if(conditionNode.evaluate(bindings) > 0){
            return trueStatement.execute(bindings);
        }else{
            return falseStatement.execute(bindings);
        }
    }
}
