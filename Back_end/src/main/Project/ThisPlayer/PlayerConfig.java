package Project.ThisPlayer;
import Project.PersonalInfo;
import Project.ThisRegion.Territory;
import Project.ThisRegion.Region;

import java.util.*;

public class PlayerConfig implements Player{
    // Variable
    protected int id;
    protected String name;
    protected long budget;

    public PlayerConfig(int id, String name, long budget){
        this.id = id;
        this.name = name;
        this.budget = budget;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getBudget() {
        return budget;
    }

    @Override
    public boolean updateBudget(long amount) {
        return false;
    }

    /*
    protected int playerTurn = 0;
    protected boolean outOfBudget = false;
    protected PersonalInfo CityCrew = null;
    protected PersonalInfo CityCenter = null;
    protected Region currentRegion;
    protected Territory territory;
    protected HashMap<String, Integer> PersonalValue = null;
    protected ArrayList<PersonalInfo> RegionProcess = new ArrayList<>();

    // Constructor
    public PlayerConfig(String name, long budget, long x, long y, Territory territory){
        this.name = name;
        this.budget = budget;
        this.territory = territory;
        CityCrew = new PersonalInfo();   // need implement
        CityCenter = new PersonalInfo(); // need implement
        PersonalValue = new HashMap<>();
    }

    // method
    public boolean hasEnoughBudget(long amount){
        return budget >= amount;
    }

    public void decreaseBudget(long amount){
        budget -= amount;
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
    public long getbudget() {
        return budget;
    }

    public void setbudget(long budget) {
        this.budget = budget;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
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
*/


}
