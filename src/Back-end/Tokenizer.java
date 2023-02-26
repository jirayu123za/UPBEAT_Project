import java.util.*;

public class Tokenizer {
    protected List<String> tokens;
    protected String next;

    public Tokenizer() throws SyntaxError{
        tokens = new ArrayList<>();
        computeNext();
    }

    private void computeNext(){
        /*need implement*/
    }

    private String peek(){
        return next;
    }

    private String consume(){
        String result = next;
        computeNext();

        return result;
    }

    boolean peek(String r){
        if(peek() != null)
            return peek().matches(r);
        return false;
    }

    void consume(String r) throws SyntaxError{
            if(peek().matches(r)){
                consume();
            }else {
                throw new SyntaxError("consume(r): token does not match r");
            }
    }


}
