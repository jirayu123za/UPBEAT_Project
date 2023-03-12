package Project.parseEvaluator.nodes;

import java.util.*;
public class VariableExpressionNode implements Node{
    protected Map<String,Long> variable = null;
    protected String identifier;

    public VariableExpressionNode(){}
    public VariableExpressionNode(String identifier, Map<String,Long> variable){
        this.variable = variable;
        this.identifier = identifier;
        if(!variable.containsKey(identifier)){
            variable.put(identifier, (long) 0.0);
        }
    }

    @Override
    public long evaluate(Map<String, Integer> bindings) {
        if(!variable.containsKey(identifier)){
            variable.put(identifier, (long) 0.0);
        }
        return variable.get(identifier);
    }

    public void assignValue(long value){
        variable.put(identifier, value);
    }

    public String getIdentifier(){
        return getIdentifier();
    }

    @Override
    public void print(int height, Map<String, Integer> bindings) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.print("<" + identifier + ">");
    }
}
