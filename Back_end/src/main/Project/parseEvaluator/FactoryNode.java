package Project.parseEvaluator;
import Project.parseEvaluator.nodes.*;

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

    // create a new action command
    public ActionCommand createActionCommand(String action){
        return new ActionCommand(action);
    }

    // create a new assignment statement node
    public Node createAssignmentStatementNode(VariableExpressionNode variableExpressionNode, Node expressionNode){
        return new AssignmentStatementNode(variableExpressionNode, expressionNode);
    }

    // create a new attack command
    public Node createAttackCommand(String action, DirectionNode direction,Node expressionNode) throws SyntaxError {
        return new AttackCommand(action, direction, expressionNode);
    }

    // create a new binary arithmetic node
    public Node createBinaryArithmeticNode(Node left, String op, Node right){
        return new BinaryArithmeticNode(left, op, right);
    }

    // create a new block statement Node
    public Node createBlockStatementNode(LinkedList<Node> list){
        return new BlockStatementNode(list);
    }

    // create a new Direction
    public DirectionNode createDirection(String direction) throws SyntaxError{
        return new DirectionNode(direction);
    }

    // create a new if statement node
    public Node createIfStatementNode(Node expressionNode, Node ifTrueStatementNode, Node ifFalseStatementNode){
        return new IfStatementNode(expressionNode, ifTrueStatementNode, ifFalseStatementNode);
    }

    // create a new info node
    public Node createInfoExpressionNode(String type, DirectionNode direction){
        return new InfoExpressionNode(type, direction);
    }

    public Node createInfoExpressionNode(String type){
        return new InfoExpressionNode(type, null);
    }

    // create a new move command
    public Node createMoveCommand(String move, DirectionNode direction){
        return new MoveCommand(move, direction);
    }

    // create a new number node
    public Node createNumberNode(double value){
        return new NumberNode(value);
    }

   // create a new PlanNode
    public PlanNode createPlanNode(){
        return new PlanNode();
    }

    // create a new VariableExpressionNode node by rand
    public VariableExpressionNode createRandNumNode(){
        return new RandNumExpressionNode();
    }

    // create a new region command
    public RegionCommand createRegionCommand(String action, Node expressionNode){
        return new RegionCommand(action, expressionNode);
    }

    // create a new variable node
    public VariableExpressionNode createVariableNode(String identifier, Map<String,Double> variable){
        return new VariableExpressionNode(identifier, variable);
    }

    // create a new while statement node
    public Node createWhileStatementNode(Node expressionNode, Node statementNode){
        return new WhileStatementNode(expressionNode, statementNode);
    }
}
