package Project.GameProcess;
import Project.ThisPlayer.*;
import Project.ThisRegion.*;
import Project.ThisRegion.Region;
import Project.ThisTurn.*;
import Project.Nodes.*;
import Project.parseEvaluator.GrammarTokenizer;
import Project.parseEvaluator.Parser;
import Project.parseEvaluator.ProcessParse;

import java.awt.geom.Point2D;
import java.util.*;

public class GameConfig implements Game {
    protected Configuration config;
    protected List<Region> territory;
    protected int cost = 1;
    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;
    protected Player win;
    protected Region cityCrew;
    protected Turn turn;
    protected Map<Player, Region> cityCenterOfRegion;

    public GameConfig(Player player1, Player player2, Configuration config, List<Region> territory){
        this.player1 = player1;
        this.player2 = player2;
        this.config = config;
        this.territory = territory;
        this.currentPlayer = this.player1;
        this.cityCenterOfRegion = new HashMap<>();
        this.turn = new TurnConfig(config, territory, player1, player2, 1);
        this.turn.StartTurn();
        this.turn.getTurn();
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2(){
        return player2;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Player getWin() {
        return win;
    }

    @Override
    public List<Region> getTerritory() {
        return territory;
    }

    public List<Region> getAdjacentRegions(Region region){
        List<Region> adjacentRegions = new ArrayList<>(6);
        Position curLoc = region.getLocation();
        for(DirectionNode direction : DirectionNode.values()){
            Position newLoc = curLoc.direction(direction);
            if(!newLoc.Check_isValidPosition(config.rows(), config.cols())){
                continue;
            }
            adjacentRegions.add(regionOn(newLoc));
        }
        return adjacentRegions;
    }

    @Override
    public Region regionOn(Position position) {
        long i = position.getPosY() * config.cols() + position.getPosX();
        return territory.get((int) i);
    }

    @Override
    public Region cityCrewRegion() {
        return cityCrew;
    }

    @Override
    public void getConstructionPlan(String constructionPlan) {
        if(win != null){
            throw new RuntimeException("Game is end");
        }
        turn.StartTurn();
        executeConstructionPlan(constructionPlan);
        win = winner();
        turn.endTurn();
    }

    public void executeConstructionPlan(String constructionPlan){
        Parser parse = new ProcessParse(new GrammarTokenizer(constructionPlan));
        List<ExecuteNode> plans = parse.parse();
        for(ExecuteNode plan : plans){
            plan.execute(this);
        }
    }

    public Player winner(){
        if(player1.getBudget() == 0){
            return player2;
        }else if(player2.getBudget() == 0){
            return player1;
        } //
        return null;
    }

    /*
    public long CalculateShortestPathStar(Position PStart, Position PEnd){

        return -1;
    }
     */

    /*
    public long getShortestPathStar(HashMap<Position, Position> cameFrom, Position cur){
        long distance = 0;
        // processing.....
        return distance;
    }
     */


    public double getShortestPath(Position PStart, Position PEnd){
        return Point2D.distance(PStart.getPosX(), PEnd.getPosX(), PStart.getPosY(), PEnd.getPosY());
    }

    @Override
    public Map<String, Long> identifiers() {
        return currentPlayer.identifiers();
    }

    @Override
    public Map<String, Long> specialIdentifiers() {
        Map<String, Long> bindings = new HashMap<>();
        bindings.put("rows", config.cols());
        bindings.put("cols", config.rows());
        bindings.put("currentRow", cityCrew.getLocation().getPosX());
        bindings.put("currentCol", cityCrew.getLocation().getPosY());
        bindings.put("budget", currentPlayer.getBudget());
        bindings.put("deposit", cityCrew.getDeposit());
        bindings.put("maxDeposit", config.max_dep());
        bindings.put("int", config.interest_pct(turn.getTurn(), cityCrew.getDeposit()));
        bindings.put("random", new Random().nextLong(1000));
        return bindings;
    }

    public void moveCityCrew(Position position){
        if(!position.Check_isValidPosition(config.rows(), config.cols())){
            return;
        }
        cityCrew = regionOn(position);
    }

    @Override
    public boolean attack(DirectionNode direction, long totalValue) {
        if(totalValue + cost > currentPlayer.getBudget() || totalValue < 0){
            currentPlayer.updateBudget(-cost);
            return false;
        }

        // see position target
        Position cityCrewShowLocation = cityCrew.getLocation();
        Position targetLocation = cityCrewShowLocation.direction(direction);

        if(targetLocation.Check_isValidPosition(config.rows(), config.cols())){
            Region targetRegion = regionOn(targetLocation);

            if(totalValue < targetRegion.getDeposit()){
                // update budget
                currentPlayer.updateBudget(-cost - totalValue);
                // update deposit
                targetRegion.updateDeposit(-totalValue);
            }else if(totalValue >= targetRegion.getDeposit()){
                targetRegion.updateDeposit(-totalValue);
                targetRegion.updateOwner(null);
                currentPlayer.updateBudget(-cost - totalValue);
            }
        }
        return true;
    }

    @Override
    public boolean collect(long totalValue) {
        if(currentPlayer.getBudget() < 1 || totalValue < 0){
            return false;
        }
        currentPlayer.updateBudget(-cost);
        Region targetRegion = cityCrew;
        if(totalValue > targetRegion.getDeposit()){
            return true;
        }
        targetRegion.updateDeposit(-totalValue);
        currentPlayer.updateBudget(totalValue);
        if(targetRegion.getDeposit() == 0){
            targetRegion.updateOwner(null);
        }
        return true;
    }

    @Override
    public boolean invest(long totalValue)  {
        currentPlayer.updateBudget(-cost);

        // Check if the player has enough budget
        if(currentPlayer.getBudget() < cost){
            return false;
        }

        // Check adjacency requirement
        boolean leastOneAdjacent = cityCrew.getOwner() == currentPlayer;
        for(Region adjacent : getAdjacentRegions(cityCrew)){
            if(leastOneAdjacent){
                break;
            }
            leastOneAdjacent = adjacent.getOwner() == currentPlayer;
        }
        if(!leastOneAdjacent){
            return false;
        }

        // Check budget requirement
        if (currentPlayer.getBudget() < totalValue + 1) {
            return false;
        }
        currentPlayer.updateBudget(-totalValue);
        cityCrew.updateOwner(currentPlayer);
        cityCrew.updateDeposit(totalValue);
        return true;
    }

    @Override
    public boolean relocate() {
        // check has enough budget
        if(!currentPlayer.updateBudget(-cost)){
            return false;
        }

        Position curCityCrewLoc = cityCrew.getLocation();
        Position curCityCenter = cityCenterOfRegion.get(currentPlayer).getLocation();
        //long path = getShortestPathStar(curCityCrewLoc, curCityCenter);
        //long cost = 5 * path + 10;

        // execute if player has enough budget
        if(currentPlayer.getBudget() >= cost && cityCrew.getOwner() == currentPlayer){
            currentPlayer.updateBudget(-cost);
            cityCrew.setCityCenter(currentPlayer);
            cityCenterOfRegion.get(currentPlayer).changeCityCenter();
        }
        return false;
    }

    @Override
    public boolean move(DirectionNode direction) {
        if(currentPlayer.getBudget() < cost){
            return false;
        }
        currentPlayer.updateBudget(-cost);

        Position newLoc = cityCrew.getLocation().direction(direction);
        if(newLoc.Check_isValidPosition(config.rows(), config.cols())){
            Region newRegion = regionOn(newLoc);
            if(newRegion.getOwner() == null || newRegion.getOwner() == currentPlayer){
                cityCrew = newRegion;
            }
        }
        return true;
    }

    @Override
    public long opponent() {
        Position[] path = new Position[6];
        int distance = 0;
        boolean cutoff;

        for(int i = 0; i < 6; i++){
            path[i] = cityCrew.getLocation();
        }
        do{
           for(int i =0; i < 6; i++){
               if(path[i] == null){
                    continue;
               }

               long index = path[i].getPosY() * config.cols() + path[i].getPosX();
               Player owner = territory.get((int) index).getOwner();
               if(owner != null && owner != currentPlayer){
                   // need modify
                   return i + 1L + distance * 10L;
               }
               path[i] = path[i].direction(DirectionNode.values()[i]);
           }

           for(int i = 0; i < 6; i++){
                if(path[i] == null){
                    continue;
                }
                path[i] = path[i].Check_isValidPosition(config.rows(), config.cols()) ? path[i] : null;
           }
           cutoff = true;

           for(Position position : path){
               cutoff &= position == null;
           }
           distance++;

        }while(!cutoff);
        return 0;
    }

    @Override
    public long nearby(DirectionNode direction) {
        Position curLoc = cityCrew.getLocation();
        int distance = 0;
        Position newLoc = curLoc.direction(direction);

        while(newLoc.Check_isValidPosition(config.rows(), config.cols())){
            Region region = regionOn(newLoc);

            if(region.getOwner() != null && region.getOwner() != currentPlayer){
                // need modify
                return ((distance + 1L) * 100 + (long) (Math.log10(region.getDeposit() + 1)) + 1);
            }
            distance++;
            newLoc = newLoc.direction(direction);
        }
        return 0;
    }

    @Override
    public long budget() {
        return currentPlayer.getBudget();
    }
}
