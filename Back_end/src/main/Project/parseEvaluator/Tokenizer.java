package Project.parseEvaluator;
import java.util.*;

public interface Tokenizer{
    boolean hasNextToken();
    String peek();
    boolean peek(String RegularExpression);
    String consume();
    boolean consume(String RegularExpression);
    int getNewline();
}
