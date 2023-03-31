package Project.Nodes;
import Project.GameProcess.Game;
import Project.Nodes.Node.*;

public class NearbyNode extends ExpressionNode{
    protected DirectionNode direction;

    public NearbyNode(DirectionNode direction){
        this.direction = direction;
    }

    @Override
    public long evaluate(Game bindings) {
        return bindings.nearby(direction);
    }

    @Override
    public String toString() {
        return "nearby " + direction;
    }
}
