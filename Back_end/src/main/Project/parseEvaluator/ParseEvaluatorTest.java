package Project.parseEvaluator;
import Project.Nodes.*;
import org.junit.jupiter.api.Test;
import Project.ConstructionPlanCombiner;
import java.util.*;

public class ParseEvaluatorTest {
    public static Exception EXCEPTION_NULL = new NullPointerException();
    public static Exception EXCEPTION_SYNTAX = new SyntaxError();

    public static void main(String[] args){

    }

    @Test
    public void Test_sample_construction_plan(){
        ConstructionPlanCombiner combiner = new ConstructionPlanCombiner();
        String[] fileNames = {"Back_end/src/constructionPlanCode.txt"};
        String combinedContent = combiner.combineFiles(fileNames);
        ParseEvaluator parseEvaluator = new ParseEvaluator(combinedContent);
        try {
            PlanNode planNode = (PlanNode) parseEvaluator.parsePlan();
            planNode.print(0,null);
            planNode.evaluate(new HashMap<>());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
