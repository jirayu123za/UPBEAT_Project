package Project.parseEvaluator;
import Project.parseEvaluator.nodes.*;
import java.util.*;

public class ParseEvaluator {
    protected Tokenizer tkz;
    protected FactoryNode factory;

    public ParseEvaluator(String src) {
        this.tkz = new Tokenizer(src);
        factory = FactoryNode.instance();
        // System.out.println("File: " + src);
    }

    // Plan → Statement+
    public Node parsePlan() throws SyntaxError {
        System.out.println("parsePlan " + tkz.peek() + "\n");
        PlanNode planNode = factory.createPlanNode();

        while (tkz.hasNextToken()) {
            planNode.addStatement(parseStatement());
        }
        System.out.println("Return parsePlan");
        return planNode;
    }

    // Statement → Command | BlockStatement | IfStatement | WhileStatement
    public Node parseStatement() throws SyntaxError {
        System.out.println("parseStatement " + tkz.peek() + "\n");
        String s = tkz.peek();

        if (s.matches(RegularExpression.IF_REGEX)) {
            System.out.println("Return parseIfStatement");
            return parseIfStatement();
        } else if (s.matches(RegularExpression.WHILE_REGEX)) {
            System.out.println("Return parseWhileStatement");
            return parseWhileStatement();
        } else if (s.matches(RegularExpression.PARENTHESES_REGEX)) {
            System.out.println("Return parseBlockStatement");
            return parseBlockStatement();
        } else {
            System.out.println("Return parseCommand");
            return parseCommand();
        }
    }

    public Node parseCommand() throws SyntaxError {
        System.out.println("parseCommand " + tkz.peek() + "\n");
        String s = tkz.peek();
        if (s.matches(RegularExpression.ACTION_REGEX)) {
            System.out.println("Return parseAction");
            return parseAction();
        } else {
            System.out.println("Return parseAssignment");
            return parseAssignment();
        }
    }

    // AssignmentStatement → <identifier> = Expression
    public Node parseAssignment() throws SyntaxError {
        System.out.println("parseAssignment " + tkz.peek());

        VariableExpressionNode variableExpressionNode = parseVariable();
        tkz.consume(RegularExpression.ASSIGN_REGEX);
        Node expressionNode = parseExpression();
        return factory.createAssignmentStatementNode(variableExpressionNode, expressionNode);
    }

    // ActionCommand → DoneCommand | RelocateCommand | MoveCommand | RegionCommand | AttackCommand
    public Node parseAction() throws SyntaxError{
        System.out.println("parseAction " + tkz.peek() + "\n");
        String s = tkz.peek();

        if(s.matches(RegularExpression.DONE_REGEX)){
            tkz.consume(RegularExpression.DONE_REGEX);
            return parseDoneCommand();
        }else if(s.matches(RegularExpression.RELOCATE_REGEX)){
            tkz.consume(RegularExpression.RELOCATE_REGEX);
            return parseRelocateCommand();
        }else if(s.matches(RegularExpression.MOVE_REGEX)){
            tkz.consume(RegularExpression.MOVE_REGEX);
            return parseMoveCommand();
        }else if(s.matches(RegularExpression.INVEST_REGEX)
                || s.matches(RegularExpression.COLLECT_REGEX)){
            tkz.consume(RegularExpression.REGION_REGEX);
            return parseRegionCommand();
        }else if(s.matches(RegularExpression.SHOOT_REGEX)){
            tkz.consume(RegularExpression.SHOOT_REGEX);
            return parseAttackCommand();
        }else{
            throw new SyntaxError("SyntaxError: " + s);
        }
    }

    // done
    public Node parseDoneCommand() throws SyntaxError{
        String s = tkz.peek();

        if(s.matches(RegularExpression.DONE_REGEX)){
            tkz.consume(RegularExpression.DONE_REGEX);
            return factory.createActionCommand(RegularExpression.DONE_REGEX);
        }else{
            throw new SyntaxError("SyntaxError");
        }
    }

    // relocate
    public Node parseRelocateCommand() throws SyntaxError{
        String s = tkz.peek();

        if(s.matches(RegularExpression.RELOCATE_REGEX)){
            tkz.consume(RegularExpression.RELOCATE_REGEX);
            return factory.createActionCommand(RegularExpression.RELOCATE_REGEX);
        }else {
            throw new SyntaxError("SyntaxError");
        }
    }

    // MoveCommand → move Direction
    public Node parseMoveCommand() throws SyntaxError {
        System.out.println("parseMove " + tkz.peek() + "\n");
        String s = tkz.peek();

        if (s.matches(RegularExpression.MOVE_REGEX)) {
            tkz.consume(RegularExpression.MOVE_REGEX);
            return factory.createMoveCommand("move", parseDirection());
        } else {
            throw new SyntaxError("SyntaxError: " + s);
        }

    }

    // RegionCommand → invest Expression | collect Expression
    public Node parseRegionCommand() throws SyntaxError {
        System.out.println("parseRegion " + tkz.peek() + "\n");
        String s = tkz.peek();

        if (s.matches(RegularExpression.INVEST_REGEX)) {
            tkz.consume(RegularExpression.INVEST_REGEX);
            return factory.createRegionCommand("invest", parseExpression());
        } else if (s.matches(RegularExpression.COLLECT_REGEX)) {
            tkz.consume(RegularExpression.COLLECT_REGEX);
            return factory.createRegionCommand("collect", parseExpression());
        } else {
            throw new SyntaxError("SyntaxError: " + s);
        }
    }

    // AttackCommand → shoot Direction Expression
    public Node parseAttackCommand() throws SyntaxError {
        System.out.println("parseAttack " + tkz.peek() + "\n");

        tkz.consume(RegularExpression.SHOOT_REGEX);
        Node expressionNode = parseExpression();
        String action = "";
        DirectionNode direction = parseDirection();
        return factory.createAttackCommand(action, direction, expressionNode);
    }


    // Direction → up|down|upleft|upright|downleft|downright
    public DirectionNode parseDirection() throws SyntaxError {
        String s = tkz.peek();

        if (s.matches(RegularExpression.DIRECTION_REGEX)) {
            tkz.consume(RegularExpression.DIRECTION_REGEX);
            return factory.createDirection(RegularExpression.DIRECTION_REGEX);
        } else {
            throw new SyntaxError("SyntaxError: " + s);
        }
    }

    // BlockStatement → { Statement* }
    public Node parseBlockStatement() throws SyntaxError {
        tkz.consume("{");
        LinkedList<Node> state = new LinkedList<>();

        while (!tkz.peek("}")){
            state.add(parseStatement());
        }
        tkz.consume("}");
        return factory.createBlockStatementNode(state);
    }

    // IfStatement → if ( Expression ) then Statement else Statement
    public Node parseIfStatement() throws SyntaxError{
        System.out.println("parseIf " + tkz.peek() + "\n");

        tkz.consume(RegularExpression.IF_REGEX);
        tkz.consume("[(]");
        Node expressionNode = parseExpression();
        tkz.consume("[)]");
        tkz.consume(RegularExpression.THEN_REGEX);
        Node ifTrueStatementNode = parseStatement();
        tkz.consume(RegularExpression.ELSE_REGEX);
        Node ifFalseStatementNode = parseStatement();
        return factory.createIfStatementNode(expressionNode, ifTrueStatementNode, ifFalseStatementNode);
    }

    // WhileStatement → while ( Expression ) Statement
    public Node parseWhileStatement() throws SyntaxError{
        System.out.println("parseWhile " + tkz.peek() + "\n");

        tkz.consume(RegularExpression.WHILE_REGEX);
        tkz.consume("[(]");
        Node expressionNode = parseExpression();
        tkz.consume("[)]");
        Node statementNode = parseStatement();
        return factory.createWhileStatementNode(expressionNode, statementNode);
    }

    // Expression → Expression + Term | Expression - Term | Term
    public Node parseExpression() throws SyntaxError, EvaluationError{
        System.out.println("parseExpression " + tkz.peek() + "\n");

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
        System.out.println("parseTerm " + tkz.peek() + "\n");
        Node term = parseFactory();
        String op = tkz.consume();

        while(tkz.peek() != null && tkz.peek().equals("*") || tkz.peek().equals("/") || tkz.peek().equals("%")){
            switch (op) {
                case "*":
                    return factory.createBinaryArithmeticNode(term, "*", parseFactory());
                case "/":
                    return factory.createBinaryArithmeticNode(term, "/", parseFactory());
                case "%":
                    return factory.createBinaryArithmeticNode(term, "%", parseFactory());
            }
        }
        return term;
    }


    // Factor → Power ^ Factor | Power
    public Node parseFactory() throws SyntaxError, EvaluationError{
        System.out.println("parseFactor " + tkz.peek() + "\n");
        Node power = parsePower();

        if(tkz.peek().equals("\\^")){
            tkz.consume();
            return factory.createBinaryArithmeticNode(power, "^", parseFactory());
        }
        return power;
    }


    // Power → <number> | <identifier> | ( Expression ) | InfoExpression
    public Node parsePower() throws SyntaxError, EvaluationError{
        System.out.println("parsePower " + tkz.peek() + "\n");

        if(tkz.peek(RegularExpression.INFOEXPRESSION_REGEX)){
            Node infoNode = null;
            if(tkz.peek(RegularExpression.OPPONENT_REGEX)){
                tkz.consume();
                infoNode = factory.createInfoExpressionNode("opponent", parseDirection());
            }else if(tkz.peek(RegularExpression.NEARBY_REGEX)) {
                tkz.consume();
                infoNode = factory.createInfoExpressionNode("nearby", parseDirection());
            }
            return infoNode;
        }else if(tkz.peek(RegularExpression.NUMBER_REGEX)){
            Node numberNode = factory.createNumberNode(Double.parseDouble(tkz.peek()));
            tkz.consume(RegularExpression.NUMBER_REGEX);
            return numberNode;
        }else if(tkz.peek(RegularExpression.VARIABLE_REGEX)){
            return parseVariable();
        }else if(tkz.peek() != null && !tkz.peek().equals("(")){
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
        System.out.println("parseInfoExpression " + tkz.peek() + "\n");
        String type = tkz.peek();

        try{
        if(type.matches(RegularExpression.OPPONENT_REGEX)){
            return factory.createInfoExpressionNode(tkz.consume());
        }else{
            return factory.createInfoExpressionNode(tkz.consume(), parseDirection());
        }
        }catch(SyntaxError e){
            throw new SyntaxError("parseInfoExpression() has something wrong");
        }
    }

    // parseVariable
    public VariableExpressionNode parseVariable() throws SyntaxError{
        System.out.println("parseVariable " + tkz.peek() + "\n");
        String identifier = tkz.peek();

        if(identifier.matches(RegularExpression.RANDOM_REGEX)){
            tkz.consume(RegularExpression.RANDOM_REGEX);
            return factory.createRandNumNode();
        }
        tkz.consume(RegularExpression.VARIABLE_REGEX);
        return factory.createVariableNode(identifier, new HashMap<>());
    }
}
