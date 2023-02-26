package parseEvaluator;

import java.util.*;
import java.util.regex.Matcher;

public class Tokenizer {
    protected List<String> tokens;
    protected String next, ConstructionPlan;
    protected Matcher matcher;

    public Tokenizer(String constructionPlan) throws SyntaxError {
        this.ConstructionPlan = constructionPlan;
        tokens = new ArrayList<>();

        matcher = RegularExpression.ALL_PATTERN.matcher(ConstructionPlan);
        computeNext();
    }

    private void computeNext(){
        if(matcher.find()){
            next = matcher.group();
        }else{
            next = null;
        }
    }

    private String peek(){
        return next;
    }

    private String consume(){
        String result = next;
        computeNext();

        return result;
    }

    boolean peek(String RegularExpression){
        if(peek() != null)
            return peek().matches(RegularExpression);
        return false;
    }

    void consume(String RegularExpression) throws SyntaxError {
            if(peek().matches(RegularExpression)){
                consume();
            }else {
                throw new SyntaxError("consume(s): token does not match s");
            }
    }


}
