package Project.parseEvaluator.nodes;

import java.util.*;
public class VariableNode implements Node{
    protected Map<String,Double> variable = null;
    protected String identifier;

    public VariableNode(){}
    public VariableNode(String identifier, Map<String,Double> variable){
        this.variable = variable;
        this.identifier = identifier;
        if(!variable.containsKey(identifier)){
            variable.put(identifier, 0.0);
        }
    }

    @Override
    public double evaluate() {
        if(!variable.containsKey(identifier)){
            variable.put(identifier, 0.0);
        }
        return variable.get(identifier);
    }

    public void assignValue(double value){
        variable.put(identifier, value);
    }

    public String getIdentifier(){
        return getIdentifier();
    }

    @Override
    public void print(int height) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.print("<" + identifier + ">");
    }
}
