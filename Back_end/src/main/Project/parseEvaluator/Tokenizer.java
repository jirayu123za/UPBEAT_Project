package Project.parseEvaluator;
import java.util.*;

public class Tokenizer{
    protected final Queue<String> tokens;
    protected String ConstructionPlan;

    public Tokenizer(String ConstructionPlan){
        this.ConstructionPlan = ConstructionPlan;
        tokens = new LinkedList<>();
        String[] strings = RegularExpression.SPLIT_PATTERN.split(ConstructionPlan);
        for(String s : strings){
            if(!s.trim().isEmpty()){
                tokens.add(s);
            }
        }
    }

    public boolean hasNextToken(){
        return tokens.isEmpty();
    }

    public boolean isNumber(String RegularExpression){
        try{
            Double.parseDouble(RegularExpression);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public String peek() throws SyntaxError{
        if(tokens.isEmpty()){
            throw new SyntaxError("!!---EMPTY---!!");
        }
        return tokens.element();
    }

    public boolean peek(String RegularExpression){
        return !tokens.isEmpty() && tokens.element().equals(RegularExpression);
    }

    public String consume(){
        return tokens.remove();
    }

    public void consume(String RegularExpression) throws SyntaxError {
        if (!peek(RegularExpression)) {
            throw new SyntaxError("!!---Error---!!");
        }
        consume();
    }



}
