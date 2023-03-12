package Project.parseEvaluator;
import Project.Nodes.*;

import java.util.*;
public class FactoryNode {
    private static FactoryNode instance;
    private FactoryNode(){}

    public static FactoryNode instance(){
        if (instance == null) {
            instance = new FactoryNode();
        }
        return instance;
    }

    // create a new assignment statement node
    public ExecuteNode createAssignmentStatementNode(String identifier, ExpressionNode expressionNode){
        return new AssignmentStatementNode(identifier, expressionNode);
    }

    // create a new attack command
    public ExecuteNode createAttackCommand(DirectionNode direction,ExpressionNode expressionNode){
        return new AttackCommand(direction, expressionNode);
    }

    // create a new binary arithmetic node
    public ExpressionNode createBinaryArithmeticNode(ExpressionNode left, String op, ExpressionNode right){
        return new BinaryArithmeticNode(left, op, right);
    }

    // create a new block statement Node
    public ExecuteNode createBlockStatementNode(LinkedList<ExecuteNode> statements){
        return new BlockStatementNode(statements);
    }

    // create a new collect command
    public ExecuteNode createCollectCommand(ExpressionNode expressionNode){
        return new CollectCommand(expressionNode);
    }

    // create a new if statement node
    public ExecuteNode createConditionStatementNode(ExpressionNode expressionNode, ExecuteNode trueStatement, ExecuteNode falseStatement){
        return new ConditionStatementNode(expressionNode, trueStatement, falseStatement);
    }

    // create a new done command
    public ExecuteNode createDoneCommand(){
        return new DoneCommand();
    }

    // create a new if statement node
    public ExecuteNode createIfStatementNode(ExpressionNode expressionNode, ExecuteNode trueStatement, ExecuteNode falseStatement){
        return new IfStatementNode(expressionNode, trueStatement, falseStatement);
    }

    // create a new invest command
    public ExecuteNode createInvestCommand(ExpressionNode expressionNode){
        return new InvestCommand(expressionNode);
    }

    // create a new move command
    public ExecuteNode createMoveCommand(DirectionNode direction){
        return new MoveCommand(direction);
    }

    // create a new nearby node
    public ExpressionNode createNearbyNode(DirectionNode direction){
        return new NearbyNode(direction);
    }

    // create a new opponent node
    public ExpressionNode createOpponentNode(){
        return new OpponentNode();
    }

    // create a new PlanNode
    public PlanNode createPlanNode(){
        return new PlanNode();
    }


    // create a new relocate command
    public ExecuteNode createRelocateCommand(){
        return new RelocateCommand();
    }

    // create a new variable node
    public ExpressionNode createVariableNode(long variable){
        return new VariableExpressionNode(variable);
    }

    // create a new variable node
    public ExpressionNode createVariableNode(String identifier){
        return new VariableExpressionNode(identifier);
    }

    // create a new while statement node
    public ExecuteNode createWhileStatementNode(ExpressionNode expressionNode, ExecuteNode statementNode){
        return new WhileStatementNode(expressionNode, statementNode);
    }
}
