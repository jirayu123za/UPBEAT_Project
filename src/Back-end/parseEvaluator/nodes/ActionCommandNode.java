package parseEvaluator.nodes;

public class ActionCommandNode implements Node{
    protected String action;
    protected CityCrew city;

    public ActionCommandNode(String action, CityCrew city){
        this.action = action;
        this.city = city;
    }


    @Override
    public double evaluate() {
        /*Implement*/
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
