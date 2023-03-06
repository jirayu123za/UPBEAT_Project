package Project.parseEvaluator;
import java.util.*;
import java.util.regex.Matcher;


public class PlanTokenizer extends Tokenizer{
    protected int current;
    protected String next, ConstructionPlan;
    protected Matcher matcher;

    public PlanTokenizer(String constructionPlan) throws SyntaxError{
        super(constructionPlan);
        this.ConstructionPlan = constructionPlan;
        matcher = RegularExpression.ALL_PATTERN.matcher(ConstructionPlan);
        current = 0;
        computeNext();
    }

    public boolean hasNextToken(){
        return next != null;
    }

    public void computeNext() throws IllegalArgumentException{
        while(matcher.find()) {
            String match = matcher.group();
            if (!match.startsWith("#") && !Character.isWhitespace(match.charAt(0))) {
                next = match;
                return;
            }
        }
        next = null;
    }

    public String peek(){
        if(hasNextToken()) return next;
        throw new NoSuchElementException("Empty");
    }

    public boolean peek(String RegularExpression){
        if(peek() != null)
            return peek().matches(RegularExpression);
        return false;
    }

    public String consume(){
        if(hasNextToken()) {
            String result = next;
            computeNext();
            return result;
        }
        throw new NoSuchElementException("No more tokens");
    }

    public void consume(String RegularExpression) throws SyntaxError{
            if(peek().matches(RegularExpression)){
                consume();
            }else {
                throw new SyntaxError("consume(" + RegularExpression + "): token does not match string ");
            }
    }

    public void Comment(){
        while(current < ConstructionPlan.length() && ConstructionPlan.charAt(current) != '\n'){
            current++;
        }
    }

}
