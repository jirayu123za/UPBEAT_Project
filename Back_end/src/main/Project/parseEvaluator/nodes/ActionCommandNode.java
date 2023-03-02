package Project.parseEvaluator.nodes;

public class ActionCommandNode implements Node{
    protected String action;

    public ActionCommandNode(String action){
        this.action = action;
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
