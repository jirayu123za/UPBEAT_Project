package Project.Nodes;
import Project.GameProcess.Game;
import java.util.Map;

public class AssignmentStatementNode extends ExecuteNode{
    protected String identifier;
    protected ExpressionNode expressionNode;

    public AssignmentStatementNode(String identifier, ExpressionNode expressionNode){
        this.identifier = identifier;
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean execute(Game bindings) {
        Map<String, Long> store = bindings.identifiers();
        store.put(identifier, expressionNode.evaluate(bindings));
        return true;
    }

    public ExpressionNode execute(Map<String, Long> map){
        if(!(expressionNode instanceof VariableExpressionNode)){
            throw new RuntimeException(expressionNode.toString());
        }
        map.put(identifier, ((VariableExpressionNode) expressionNode).evaluate());
        return next;
    }
}