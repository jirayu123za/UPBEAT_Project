package Project.parseEvaluator;

import Project.parseEvaluator.nodes.*;
import Project.CityCrew;

import java.util.*;
public class NodeFactory {
    private static NodeFactory instance;
    private NodeFactory(){}

    public static NodeFactory instance(){
        if (instance == null) {
            instance = new NodeFactory();
        }
        return instance;
    }

    /*nodes.PlanNode/nodes.PlanNode. it's the same thing?
      return nodes.PlanNode */
    public PlanNode createPlanNode(){
        return new PlanNode();
    }

    // create a new assignment statement node
    public Node createAssignmentStatementNode(VariableNode variableNode, Node expressionNode){
        return new AssignmentStatementNode(variableNode, expressionNode);
    }

    // create a new move command node
    public Node createMoveCommandNode(String direction, CityCrew city){
        return new MoveCommandNode(direction, city);
    }

    // create a new attack command node
    public Node createAttackCommandNode(String direction, Node expressionNode){
        return new AttackCommandNode(direction, expressionNode);
    }

    // create a new while statement node
    public Node createWhileStatementNode(Node expressionNode, Node statementNode){
        return new WhileStatementNode(expressionNode, statementNode);
    }

    // create a new if statement node
    public Node createIfStatementNode(Node expressionNode, Node ifTrueStatementNode, Node ifFalseStatementNode){
        return new IfStatementNode(expressionNode, ifTrueStatementNode, ifFalseStatementNode);
    }

    // create a new binary arithmetic node
    public Node createBinaryArithmeticNode(Node left, String op, Node right){
        return new BinaryArithmeticNode(left, op, right);
    }

    // create a new variable node
    public VariableNode createVariableNode(String identifier, Map<String,Double> variable){
        return new VariableNode(identifier, variable);
    }

    // create a new number node
    public Node createNumberNode(double value){
        return new NumberNode(value);
    }

    // create a new info node
    public Node createInfoExpressionNode(String type, String direction, CityCrew city){
        return new InfoExpressionNode(type, direction, city);
    }

    // create a new VariableNode node by rand
    public VariableNode createRandNumNode(){
        return new RandNumNode();
    }

    // create a new collect command node
    public Node createCollectCommandNode(Node expressionNode, CityCrew city){
        return new CollectCommandNode(expressionNode, city);
    }

    // create a new invest command node
    public Node createInvestCommandNode(Node expressionNode, CityCrew city){
        return new InvestCommandNode(expressionNode, city);
    }

    // create a new relocate command node
    public Node createRelocateCommandNode(CityCrew city, String direction){
        return new RelocateCommandNode(city, direction);
    }

    // create a new region command node
    public RegionCommandNode createRegionCommandNode(Node expressionNode, CityCrew city){
        return new RegionCommandNode(expressionNode, city);
    }

    // create a new done command node
    public DoneCommandNode createDoneCommandNode(CityCrew city){
        return new DoneCommandNode(city);
    }

    // create a new action command node
    public ActionCommandNode createActionCommandNode(String action, CityCrew city){
        return new ActionCommandNode(action, city);
    }
}
