package parseEvaluator;

import

public class ParseEvaluator {
    protected CityCrew city;
    protected Tokenizer tkz;
    protected NodeFactory factory;

    public ParseEvaluator(String src, CityCrew city){
        try{
            this.tkz = new Tokenizer(src);
            factory = NodeFactory.instance();
            this.city = city;
        }catch(SyntaxError e){
            System.out.println(e.getMessage());
        }
    }

    public Node parseNode() throws SyntaxError, UnmatchedParenthesesError {




        return PlanNode;
    }



}
