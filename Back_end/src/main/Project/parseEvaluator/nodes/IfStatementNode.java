package Project.parseEvaluator.nodes;

public class IfStatementNode extends ConditionStatementNode{
    public IfStatementNode(ExpressionNode conditionNode, ExecuteNode trueStatement, ExecuteNode falseStatement) {
        super(conditionNode, trueStatement, falseStatement);

    }
}
