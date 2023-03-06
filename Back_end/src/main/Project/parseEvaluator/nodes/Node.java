package Project.parseEvaluator.nodes;
import java.util.Map;

public interface Node {
    double evaluate(Map<String,Integer> bindings);
    void print(int height, Map<String,Integer> bindings);
}
