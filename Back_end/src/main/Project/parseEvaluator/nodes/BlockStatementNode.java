package Project.parseEvaluator.nodes;
import java.util.*;

public class BlockStatementNode implements Node{
    public LinkedList<Node> statements;

    public BlockStatementNode(LinkedList<Node> list){
        this.statements = list;
    }

    public LinkedList<Node> getList(){
        return statements;
    }

    @Override
    public double evaluate(Map<String, Integer> bindings){
        for(Node node : statements){
            node.evaluate(bindings);
        }
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings){
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.println(" |---BlockStatement");
        for(Node statementNode : statements){
            statementNode.print(height + 1, bindings);
        }
    }
}
