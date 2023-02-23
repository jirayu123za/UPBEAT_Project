import java.util.*;

public class BlockStatementNode implements Node{
    protected List<Node> statements;

    public BlockStatementNode(){
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
