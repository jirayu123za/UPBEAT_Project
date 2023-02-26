package Project.parseEvaluator.nodes;

import java.util.*;

public class PlanNode implements Node{
    protected List<Node> statements;

    public PlanNode(){
        statements = new ArrayList<>();
    }

    public void addStatement(Node statementNode){
        statements.add(statementNode);
    }

    @Override
    public double evaluate() {
        for(Node statement : statements){
            statement.evaluate();
        }
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
