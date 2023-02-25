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

    /*PlanNode/PlanNode. it's the same thing?
      return PlanNode */
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
    public VarNode createVarNode(String identifier, Map<String,Double> var){
        return new VarNode(identifier, var);
    }

    // create a new number node
    public NumberNode createNumberNode(double number){
        return new NumberNode(number);
    }



}
