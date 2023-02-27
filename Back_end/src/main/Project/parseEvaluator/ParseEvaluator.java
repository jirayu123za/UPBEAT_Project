package Project.parseEvaluator;
import Project.*;
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
        VariableNode variableNode = parseVariable();
        tkz.consume(RegularExpression.ASSIGN_REGEX);
        Node expressionNode = parseExpression();
        return factory.createAssignmentStatementNode(variableNode, expressionNode);
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

    // RegionCommand → invest Expression | collect Expression
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

    // AttackCommand → shoot Direction Expression
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

        return factory.createIfStatementNode(expressionNode, ifTrueStatementNode, ifFalseStatementNode);
    }

    // WhileStatement → while ( Expression ) Statement
    public Node parseWhileStatementNode() throws SyntaxError{
        tkz.consume(RegularExpression.WHILE_REGEX);
        tkz.consume("[(]");
        Node expressionNode = parseExpression();
        tkz.consume("[)]");
        Node statementNode = parsePlan();

        return factory.createWhileStatementNode(expressionNode, statementNode);
    }

    // Expression → Expression + Term | Expression - Term | Term
    public Node parseExpression() throws SyntaxError, EvaluationError{
        Node expression = parseTerm();
        while(tkz.peek() != null && (tkz.peek().equals("+") || tkz.peek().equals("-"))){
            String op = tkz.consume();
            Node rightTerm = parseTerm();

            if(op.equals("+")){
                expression = factory.createBinaryArithmeticNode(expression, "+", rightTerm);
            }else if(op.equals("-")){
                expression = factory.createBinaryArithmeticNode(expression, "-", rightTerm);
            }else{
                throw new SyntaxError("parseExpression() has something wrong");
            }
        }
        return expression;
    }

    // Term → Term * Factor | Term / Factor | Term % Factor | Factor
    public Node parseTerm() throws SyntaxError, EvaluationError{
        Node term = parseFactory();

        while(tkz.peek() != null && tkz.peek().equals("*") || tkz.peek().equals("/") || tkz.peek().equals("%")){
            String op = tkz.consume();
            Node rightTerm = parseFactory();

            switch (op) {
                case "*":
                    term = factory.createBinaryArithmeticNode(term, "*", rightTerm);
                    break;
                case "/":
                    if (rightTerm.evaluate() != 0) {
                        term = factory.createBinaryArithmeticNode(term, "/", rightTerm);
                    } else {
                        throw new EvaluationError("Divide by Zero!!");
                    }
                    break;
                case "%":
                    if (rightTerm.evaluate() != 0) {
                        term = factory.createBinaryArithmeticNode(term, "%", rightTerm);
                    } else {
                        throw new EvaluationError("Modulo by Zero!!");
                    }
                    break;
                default:
                    throw new SyntaxError("parseTerm() has something wrong");
            }
        }
        return term;
    }


    // Factor → Power ^ Factor | Power
    public Node parseFactory() throws SyntaxError, EvaluationError{
        Node power = parsePower();

        if(tkz.peek().equals("^")){
            tkz.consume();
            return factory.createBinaryArithmeticNode(power, "^", parseFactory());
        }
        return power;
    }


    // Power → <number> | <identifier> | ( Expression ) | InfoExpression
    public Node parsePower() throws SyntaxError, EvaluationError{
        if(tkz.peek(RegularExpression.INFOEXPRESSION_REGEX)){
            Node infoNode = null;
            if(tkz.peek(RegularExpression.OPPONENT_REGEX)){
                tkz.consume();
                infoNode = factory.createInfoExpressionNode("opponent", "", city);
            }else if(tkz.peek(RegularExpression.NEARBY_REGEX)) {
                tkz.consume();
                infoNode = factory.createInfoExpressionNode("nearby", parseDirection(), city);
            }
            return infoNode;
        }else if(tkz.peek(RegularExpression.NUMBER_REGEX)){
            Node numberNode = factory.createNumberNode(Double.parseDouble(tkz.peek()));
            tkz.consume(RegularExpression.NUMBER_REGEX);
            return numberNode;
        }else if(tkz.peek(RegularExpression.VARIABLE_REGEX)){
            return parseVariable();
        }else if(tkz.peek() != null || !tkz.peek().equals("(")){
            tkz.consume();
            Node expressionNode = parseExpression();
            if(tkz.peek() == null || !tkz.peek().equals(")")){
                throw new SyntaxError("Unmatched closing ')'");
            }
            tkz.consume();
            return expressionNode;
        }else if(tkz.peek(RegularExpression.INFOEXPRESSION_REGEX)){
            return parseInfoExpression();
        }else{
            throw new SyntaxError("parsePower has something wrong");
        }
    }


    // InfoExpression → opponent | nearby Direction
    public Node parseInfoExpression() throws SyntaxError{
        String type = tkz.consume();

        if(type.matches(RegularExpression.NEARBY_REGEX)){
            String direction = parseDirection();
            return factory.createInfoExpressionNode(type, direction, city);
        }else if(type.matches(RegularExpression.OPPONENT_REGEX)){
            return factory.createInfoExpressionNode(type,"", city);
        }else{
            throw new SyntaxError("parseInfoExpression() has something wrong");
        }
    }

    // parseVariable
    public VariableNode parseVariable() throws SyntaxError{
        String identifier = tkz.peek();

        if(identifier.matches(RegularExpression.RANDOM_REGEX)){
            tkz.consume(RegularExpression.RANDOM_REGEX);

            return factory.createRandNumNode();
        }
        return null;
    }

    // All boolean method

}
