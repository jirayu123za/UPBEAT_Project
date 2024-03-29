package Project.Nodes;
import Project.GameProcess.Game;
import Project.Nodes.Node.*;

public class OpponentNode extends ExpressionNode {
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
