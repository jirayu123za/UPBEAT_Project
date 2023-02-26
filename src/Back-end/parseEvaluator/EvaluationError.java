package parseEvaluator;

import parseEvaluator.SyntaxError;

public class EvaluationError extends SyntaxError {
    public EvaluationError(String message){
        super(message);
    }
}
