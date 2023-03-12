package Project.parseEvaluator.nodes;
import java.util.*;

public class PlanNode implements Node{
    protected LinkedList<Node> statements;
    protected Iterator<Node> iterator;

    public PlanNode(){
        statements = new LinkedList<>();
        iterator = statements.iterator();
    }

    public void addStatement(Node statementNode){
        statements.add(statementNode);
    }

    public boolean hasNext(){
        return iterator.hasNext();
    }

    public Node nextStatement(){
        return hasNext() ? iterator.next() : null;
    }

    public void setIterator(){
        iterator = statements.iterator();
    }


    @Override
    public long evaluate(Map<String, Integer> bindings) {
        setIterator();
        while(hasNext()){
            Node statementNode = nextStatement();
            statementNode.evaluate(bindings);
        }
        return 0;
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for (int i = 0; i < height; ++i) {
            System.out.print("   ");
        }
        System.out.print(" |---Program ");
        for (Node stm : statements) {
            System.out.println();
            stm.print(height + 1, bindings);
        }
        System.out.println();
    }
}
