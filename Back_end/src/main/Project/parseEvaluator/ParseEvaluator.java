package Project.parseEvaluator;
import Project.CityCrew;
import Project.parseEvaluator.nodes.*;

public class ParseEvaluator {
    protected CityCrew city;
    protected Tokenizer tkz;
    protected NodeFactory factory;

    public ParseEvaluator(String src, CityCrew city) {
        try {
            this.tkz = new Tokenizer(src);
            factory = NodeFactory.instance();
            this.city = city;
        } catch (SyntaxError e) {
            System.out.println(e.getMessage());
        }
    }

    public Node parsePlan() throws SyntaxError, UnmatchedParenthesesError {
        PlanNode planNode = factory.createPlanNode();

        while (tkz.peek() != null) {
            String string = tkz.peek();
            if (string.matches(RegularExpression.OPERATOR_REGEX) || string.matches(RegularExpression.NUMBER_REGEX) || string.matches(RegularExpression.DIRECTION_REGEX) || string.matches(RegularExpression.INFOEXPRESSION_REGEX)) {
                tkz.consume();
                throw new SyntaxError("Invalid statement for start");
            } else if (string.matches(RegularExpression.RESERVEDWORD_REGEX)) {



            } else {
                throw new SyntaxError("Syntax error: " + string);
            }
        }
        return planNode;
    }

    public Node parseAssignment() throws SyntaxError {
        VarNode varNode = parseVariable();
        tkz.consume(RegularExpression.ASSIGN_REGEX);
        Node expressionNode = parseExpression();
        return factory.createNode(varNode, expressionNode);
    }

    public Node parseAction() throws SyntaxError {
        if (tkz.peek(RegularExpression.DONE_REGEX)) {
            /*change boolean something*/
        } else if (tkz.peek(RegularExpression.RELOCATE_REGEX)) {
            /*change boolean something*/
        } else if (tkz.peek(RegularExpression.MOVE_REGEX)) {
            /*change boolean something*/
        } else if (tkz.peek(RegularExpression.INVEST_REGEX)) {
            /*change boolean something*/
        } else if (tkz.peek(RegularExpression.COLLECT_REGEX)) {
            /*change boolean something*/
        } else if (tkz.peek(RegularExpression.SHOOT_REGEX)) {
            /*change boolean something*/
        }
        tkz.consume(RegularExpression.ACTION_REGEX);

        return null;
    }

    // MoveCommand → move Direction
    public Node parseMoveCommand() throws SyntaxError {
        if (tkz.peek(RegularExpression.MOVE_REGEX)) {
            tkz.consume(RegularExpression.MOVE_REGEX);
        }
        String direction = parseDirection();
        return factory.createMoveCommandNode(direction, city);
    }

    // Direction → up|down|upleft|upright|downleft|downright
    public String parseDirection() throws SyntaxError {
        String direction = tkz.peek();
        tkz.consume(RegularExpression.DIRECTION_REGEX);
        return direction;
    }

    public Node parseRegionCommand() throws SyntaxError {
        String invest = tkz.consume();
        String collect = tkz.consume();
        Node expressionNode = parseExpression();

        if (invest.matches(RegularExpression.INVEST_REGEX)) {
            factory.createInvestCommandNode(expressionNode, city);
        } else if (collect.matches(RegularExpression.COLLECT_REGEX)) {
            factory.createCollectCommandNode(expressionNode, city);
        }
        return factory.createRegionCommandNode(expressionNode, city);
    }

    public Node parseAttackCommandNode() throws SyntaxError{
        Node expressionNode = parseExpression();
        String direction = parseDirection();

        if(tkz.peek(RegularExpression.SHOOT_REGEX)){
            /*check boolean something*/
        }
        tkz.consume(RegularExpression.SHOOT_REGEX);

        AttackCommandNode AttackCommandNode = new AttackCommandNode(direction,expressionNode);
        return factory.createAttackCommandNode(direction, expressionNode);
    }

    // IfStatement → if ( Expression ) then Statement else Statement
    public Node parseIfStatementNode() throws SyntaxError{
        tkz.consume(RegularExpression.IF_REGEX);
        tkz.consume("[(]");
        Node expressionNode = parseExpression();
        tkz.consume("[)]");
        tkz.consume(RegularExpression.THEN_REGEX);
        Node ifTrueStatementNode = parsePlan();
        tkz.consume(RegularExpression.ELSE_REGEX);
        Node ifFalseStatementNode = parsePlan();

        return factory.createNode(expressionNode, ifTrueStatementNode, ifFalseStatementNode);
    }

    // WhileStatement → while ( Expression ) Statement


    //Expression → Expression + Term | Expression - Term | Term

    // Term → Term * Factor | Term / Factor | Term % Factor | Factor

    // Factor → Power ^ Factor | Power

    // Power → <number> | <identifier> | ( Expression ) | InfoExpression

    // InfoExpression → opponent | nearby Direction

    // All boolean method

}
