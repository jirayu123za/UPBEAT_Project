package Project.parseEvaluator;
import Project.Nodes.*;
import java.util.*;

public class ProcessParse implements Parser{
    protected Tokenizer tkz;
    protected FactoryNode factory;

    public ProcessParse(Tokenizer tkz) {
        if(!tkz.hasNextToken()){
            throw new SyntaxError.StateRequire(tkz.getNewline());
        }
        this.tkz = tkz;
        factory = FactoryNode.instance();
    }

    @Override
    public List<ExecuteNode> parse(){
        List<ExecuteNode> doState = parsePlan();

        if(tkz.hasNextToken()){
            throw new RuntimeException(tkz.peek());
        }
        return doState;
    }

    // Plan → Statement+
    public List<ExecuteNode> parsePlan(){
        List<ExecuteNode> plan = new ArrayList<>();
        plan.add(parseStatement());
        parseStatements(plan);
        return plan;
    }

    // Statement → Command | BlockStatement | IfStatement | WhileStatement
    public void parseStatements(List<ExecuteNode> statements){
        while(!tkz.peek("}") && tkz.hasNextToken()){
            statements.add(parseStatement());
        }
    }

    public ExecuteNode parseStatement(){
        if(tkz.peek("if")){
            return parseIfStatement();
        }else if(tkz.peek("while")){
            return parseWhileStatement();
        }else if(tkz.peek("{")){
            return parseBlockStatement();
        }else{
            return parseCommand();
        }
    }

    public ExecuteNode parseBlockStatement(){
        List<ExecuteNode> statements = new ArrayList<>();
        tkz.consume("{");
        parseStatements(statements);
        tkz.consume("}");
        return factory.createBlockStatementNode(statements);
    }

    public ExecuteNode parseWhileStatement(){
        tkz.consume("while");
        tkz.consume("(");
        ExpressionNode expressionNode = parseExpression();
        tkz.consume(")");
        ExecuteNode statements = parseStatement();
        return factory.createWhileStatementNode(expressionNode, statements);
    }

    public ExecuteNode parseIfStatement(){
        tkz.consume("if");
        tkz.consume("(");
        ExpressionNode expressionNode = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        ExecuteNode trueStatement = parseStatement();
        tkz.consume("else");
        ExecuteNode falseStatement = parseStatement();
        return factory.createIfStatementNode(expressionNode, trueStatement, falseStatement);
    }


    public ExecuteNode parseCommand(){
        if(RegularExpression.ACTION_REGEX.contains(tkz.peek())){
            return parseActionCommand();
        }else{
            return parseAssignmentStatement();
        }
    }

    // AssignmentStatement → <identifier> = Expression
    public ExecuteNode parseAssignmentStatement(){
        String identifier = parseIdentifier();

        if(tkz.peek("=")){
            tkz.consume();
        }else{
            throw new SyntaxError.Command404(identifier, tkz.getNewline());
        }
        ExpressionNode expressionNode = parseExpression();
        return factory.createAssignmentStatementNode(identifier, expressionNode);
    }

    private String parseIdentifier(){
        String identifier = tkz.consume();
        if(RegularExpression.RESERVEDWORD_REGEX.contains(identifier)){
            throw new SyntaxError.ReservedWord(identifier, tkz.getNewline());
        }
        return identifier;
    }

    // ActionCommand → DoneCommand | RelocateCommand | MoveCommand | RegionCommand | AttackCommand
    public ExecuteNode parseActionCommand() throws SyntaxError{
        String command = tkz.consume();

        if(command.matches(RegularExpression.DONE_REGEX)){
            return factory.createDoneCommand();
        }else if(command.matches(RegularExpression.RELOCATE_REGEX)){
            return factory.createRelocateCommand();
        }else if(command.matches(RegularExpression.MOVE_REGEX)){
            return parseMoveCommand();
        }else if(command.matches(RegularExpression.INVEST_REGEX)){
            return parseInvestCommand();
        }else if(command.matches(RegularExpression.COLLECT_REGEX)){
            return parseCollectCommand();
        }else if(command.matches(RegularExpression.SHOOT_REGEX)){
            return parseShootCommand();
        }else{
            throw new SyntaxError.CommandIsFail(command, tkz.getNewline());
        }
    }

    // MoveCommand → move Direction
    public ExecuteNode parseMoveCommand(){
        DirectionNode direction = parseDirection();
        return factory.createMoveCommand(direction);
    }

    // invest Expression
    public ExecuteNode parseInvestCommand(){
        ExpressionNode expressionNode = parseExpression();
        return factory.createInvestCommand(expressionNode);
    }

    // collect Expression
    public ExecuteNode parseCollectCommand(){
        ExpressionNode expressionNode = parseExpression();
        return factory.createCollectCommand(expressionNode);
    }

    // AttackCommand → shoot Direction Expression
    public ExecuteNode parseShootCommand(){
        DirectionNode direction = parseDirection();
        ExpressionNode expressionNode = parseExpression();
        return factory.createAttackCommand(direction, expressionNode);
    }

    // Direction → up|down|upleft|upright|downleft|downright
    public DirectionNode parseDirection(){
        String direction = tkz.consume();

        if(direction.matches(RegularExpression.UP_REGEX)) {
            return DirectionNode.up;
        }else if(direction.matches(RegularExpression.UPLEFT_REGEX)){
            return DirectionNode.upleft;
        }else if(direction.matches(RegularExpression.UPRIGHT_REGEX)){
            return DirectionNode.upright;
        }else if(direction.matches(RegularExpression.DOWN_REGEX)){
            return DirectionNode.down;
        }else if(direction.matches(RegularExpression.DOWNLEFT_REGEX)){
            return DirectionNode.downleft;
        }else if(direction.matches(RegularExpression.DOWNRIGHT_REGEX)){
            return DirectionNode.downright;
        }else{
            throw new SyntaxError.WrongDirection(direction, tkz.getNewline());
        }
    }

    // Expression → Expression + Term | Expression - Term | Term
    public ExpressionNode parseExpression(){
        ExpressionNode left = parseTerm();

        while(tkz.peek() != null && tkz.peek("+") || tkz.peek("-")) {
            String op = tkz.consume();
            ExpressionNode right = parseTerm();
            left = factory.createBinaryArithmeticNode(left, op, right);
        }
        return left;
    }

    // Term → Term * Factor | Term / Factor | Term % Factor | Factor
    public ExpressionNode parseTerm(){
        ExpressionNode left = parseFactory();

        while(tkz.peek() != null && tkz.peek().equals("*") || tkz.peek().equals("/") || tkz.peek().equals("%")){
            String op = tkz.consume();
            ExpressionNode right = parseFactory();
            left = factory.createBinaryArithmeticNode(left, op, right);
        }
        return left;
    }

    // Factor → Power ^ Factor | Power
    public ExpressionNode parseFactory(){
        ExpressionNode left = parsePower();

        if(tkz.peek().equals("^")){
            String op = tkz.consume();
            ExpressionNode right = parseFactory();
            return factory.createBinaryArithmeticNode(left, op, right);
        }
        return left;
    }

    // Power → <number> | <identifier> | ( Expression ) | InfoExpression
    public ExpressionNode parsePower(){
        if(Character.isDigit(tkz.peek().charAt(0))){
            return factory.createVariableNode(Integer.parseInt(tkz.consume()));
        }else if(tkz.peek(RegularExpression.OPPONENT_REGEX)
                ||tkz.peek(RegularExpression.NEARBY_REGEX)){
            return parseInfoExpression();
        }else if(tkz.peek("(")){
            tkz.consume("(");
            ExpressionNode expressionNode = parseExpression();
            tkz.consume(")");
            return expressionNode;
        }
        return factory.createVariableNode(tkz.consume());
    }

    // InfoExpression → opponent | nearby Direction
    public ExpressionNode parseInfoExpression(){
        if(tkz.peek(RegularExpression.OPPONENT_REGEX)){
            tkz.consume();
            return factory.createOpponentNode();
        }else if(tkz.peek(RegularExpression.NEARBY_REGEX)){
            tkz.consume();
            DirectionNode direction = parseDirection();
            return factory.createNearbyNode(direction);
        }else{
            throw new SyntaxError.WrongInfoExpression(tkz.peek(), tkz.getNewline());
        }
    }
}
