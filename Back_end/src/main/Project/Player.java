package Project;
import java.util.*;

public class Player {
    // Variable
    protected long init_budget;

    protected String name;
    protected int playerTurn = 0;
    protected boolean outOfBudget = false;
    protected PersonalInfo CityCrew = null;
    protected PersonalInfo CityCenter = null;
    protected Region currentRegion;
    protected Territory territory;
    protected HashMap<String, Integer> PersonalValue = null;
    protected ArrayList<PersonalInfo> RegionProcess = new ArrayList<>();

    // Constructor
    public Player(String name, long init_budget, long x, long y, Territory territory){
        this.name = name;
        this.init_budget = init_budget;
        this.territory = territory;
        CityCrew = new PersonalInfo();   // need implement
        CityCenter = new PersonalInfo(); // need implement
        PersonalValue = new HashMap<>();
    }

    // method
    public boolean hasEnoughBudget(long amount){
        return init_budget >= amount;
    }

    public void decreaseBudget(long amount){
        init_budget -= amount;
    }

    public void setOutOfBudget() {
        outOfBudget = true;
    }

    public List<Region> getAdjacentRegions(int x, int y){
        List<Region> adjacentRegions = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < territory.getRows() && j >= 0 && j < territory.getCols() && !(i == x && j == y)) {
                    adjacentRegions.add(territory.getRegion(i, j));
                }
            }
        }
        return adjacentRegions;
    }

    // Getters and Setters
    public long getInit_budget() {
        return init_budget;
    }

    public void setInit_budget(long init_budget) {
        this.init_budget = init_budget;
    }

    public long getBudget() {
        return init_budget;
    }

    public void setBudget(long budget) {
        this.init_budget = budget;
    }

    public boolean isOutOfBudget() {
        return outOfBudget;
    }

    public void setOutOfBudget(boolean outOfBudget) {
        this.outOfBudget = outOfBudget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public PersonalInfo getCityCrew() {
        return CityCrew;
    }

    public void setCityCrew(PersonalInfo cityCrew) {
        CityCrew = cityCrew;
    }

    public PersonalInfo getCityCenter() {
        return CityCenter;
    }

    public void setCityCenter(PersonalInfo cityCenter) {
        CityCenter = cityCenter;
    }


    public Region getCurrentRegion() {
        return currentRegion;
    }

    public void setCurrentRegion(Region currentRegion) {
        this.currentRegion = currentRegion;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }



}
