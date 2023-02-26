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
    public Node createNode(VarNode varNode, Node expressionNode){
        return new AssignmentStatementNode(varNode, expressionNode);
    }

    // create a new move command node
    public MoveCommandNode createMoveCommandNode(String direction, CityCrew city){
        return new MoveCommandNode(direction, city);
    }

    // create a new attack command node
    public AttackCommandNode createAttackCommandNode(String direction, CityCrew city){
        return new AttackCommandNode(direction, city);
    }

    // create a new while statement node
    public Node createNode(Node expressionNode, Node statementNode){
        return new WhileStatementNode(expressionNode, statementNode);
    }

    // create a new if statement node
    public Node createNode(Node expressionNode, Node ifTrueStatementNode, Node ifFalseStatementNode){
        return new IfStatementNode(expressionNode, ifTrueStatementNode, ifFalseStatementNode);
    }

    // create a new binary arithmetic node
    public Node createNode(Node left, String op, Node right){
        return new BinaryArithmeticNode(left, op, right);
    }

    // create a new variable node
    public VarNode createNode(String identifier, Map<String,Double> var){
        return new VarNode(identifier, var);
    }

    // create a new number node
    public Node createNode(double value){
        return new NumberNode(value);
    }

    // create a new info node
    public Node createNode(String direction, CityCrew city){
        return new InfoExpressionNode(direction, city);
    }

    // create a new var node by rand
    public VarNode createRandNumNode(){
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
    public Node createRegionCommandNode(Node expressionNode, CityCrew city){
        return new RegionCommandNode(expressionNode, city);
    }

    // create a new done command node
    public Node createNode(CityCrew city){
        return new DoneCommandNode(city);
    }
}
