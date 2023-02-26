package parseEvaluator.nodes;

import java.util.*;
public class VarNode implements Node{
    protected Map<String,Double> var = null;
    protected String identifier;

    public VarNode(){}
    public VarNode(String identifier, Map<String,Double> var){
        this.var = var;
        this.identifier = identifier;
        if(!var.containsKey(identifier)){
            var.put(identifier, 0.0);
        }
    }

    @Override
    public double evaluate() {
        if(!var.containsKey(identifier)){
            var.put(identifier, 0.0);
        }
        return var.get(identifier);
    }

    public void assignValue(double value){
        var.put(identifier, value);
    }

    public String getIdentifier(){
        return getIdentifier();
    }

    @Override
    public void print(int height) {

    }
}
