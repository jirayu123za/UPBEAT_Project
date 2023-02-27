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
            // System.out.println("File: " + src);
        } catch (SyntaxError e) {
            System.out.println(e.getMessage());
        }
    }

    // Need to implement to complete
    public Node parsePlan() throws SyntaxError, UnmatchedParenthesesError {
        // System.out.println("parseProgram " + tkz.peek());
        PlanNode planNode = factory.createPlanNode();

        while(tkz.peek() != null){
            String string = tkz.peek();
            // System.out.println(string);

            if(string.matches(RegularExpression.OPERATOR_REGEX) || string.matches(RegularExpression.NUMBER_REGEX) || string.matches(RegularExpression.DIRECTION_REGEX) || string.matches(RegularExpression.INFOEXPRESSION_REGEX)) {
                throw new SyntaxError("Invalid statement for start");
            }else if(string.matches(RegularExpression.RESERVEDWORD_REGEX)){
                if(string.matches(RegularExpression.IF_REGEX)){
                    planNode.addStatement(parseIfStatementNode());
                }else if(string.matches(RegularExpression.WHILE_REGEX)){
                    planNode.addStatement(parseWhileStatementNode());
                }else if(string.matches(RegularExpression.ACTION_REGEX)){
                    planNode.addStatement(parseAction());
                }else if(string.matches(RegularExpression.ELSE_REGEX)){
                    break;
                }else if(string.matches(RegularExpression.INFOEXPRESSION_REGEX)) {
                    planNode.addStatement(parseInfoExpression());
                }
            }else if(string.matches(RegularExpression.VARIABLE_REGEX)){
                planNode.addStatement(parseAssignment());
            }else if(string.matches(RegularExpression.PARENTHESES_REGEX)){
                if(string.equals("(") || string.equals(")")){
                    throw new UnmatchedParenthesesError("( or ) in statement starter");
                }else if(string.equals("{")){
                    // System.out.println(" Bracket {");
                    tkz.consume();
                    planNode.addStatement(parsePlan());

                    if(!tkz.peek().equals("}")){
                        throw new UnmatchedParenthesesError("Missing }");
                    }
                    tkz.consume();
                }else if(string.equals("}")){
                    break;
                }
            }else if(string.matches("\n")){
                tkz.consume();
            }else{
                throw new SyntaxError("Syntax error: " + string);
            }
        }
        // System.out.println("Return parseProgram");
        return planNode;
    }

    // AssignmentStatement → <identifier> = Expression
    public Node parseAssignment() throws SyntaxError {
        VariableNode variableNode = parseVariable();
        tkz.consume(RegularExpression.ASSIGN_REGEX);
        Node expressionNode = parseExpression();
        return factory.createAssignmentStatementNode(variableNode, expressionNode);
    }

    // ActionCommand → DoneCommand | RelocateCommand | MoveCommand | RegionCommand | AttackCommand
    public Node parseAction() throws SyntaxError {
        if (tkz.peek(RegularExpression.DONE_REGEX)) {
            tkz.consume(RegularExpression.DONE_REGEX);
            return factory.createDoneCommandNode(city);
        } else if (tkz.peek(RegularExpression.RELOCATE_REGEX)) {
            tkz.consume(RegularExpression.RELOCATE_REGEX);
            String direction = parseDirection();
            return factory.createRelocateCommandNode(city,direction);
        } else if (tkz.peek(RegularExpression.MOVE_REGEX)) {
            return parseMoveCommand();
        } else if (tkz.peek(RegularExpression.INVEST_REGEX) || tkz.peek(RegularExpression.COLLECT_REGEX)){
            return parseRegionCommand();
        } else if (tkz.peek(RegularExpression.SHOOT_REGEX)) {
            return parseAttackCommandNode();
        }else {
            return factory.createActionCommandNode(RegularExpression.ACTION_REGEX, city);
        }
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
            return factory.createInvestCommandNode(expressionNode, city);
        } else if (collect.matches(RegularExpression.COLLECT_REGEX)) {
            return factory.createCollectCommandNode(expressionNode, city);
        }
        return factory.createRegionCommandNode(expressionNode, city);
    }

    // AttackCommand → shoot Direction Expression
    public Node parseAttackCommandNode() throws SyntaxError{
        tkz.consume(RegularExpression.SHOOT_REGEX);
        Node expressionNode = parseExpression();
        String direction = parseDirection();
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
        tkz.consume(RegularExpression.VARIABLE_REGEX);
        return factory.createVariableNode(identifier, city.getVariables());
    }
}
