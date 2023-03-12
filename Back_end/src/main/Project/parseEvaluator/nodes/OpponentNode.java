package Project.parseEvaluator.nodes;
import Project.GameProcess.Game;

public class OpponentNode extends ExpressionNode{
    public OpponentNode(){

    }
    @Override
    public long evaluate(Game bindings) {
        return bindings.opponent();
    }

    @Override
    public String toString() {
        return "opponent";
    }
}
