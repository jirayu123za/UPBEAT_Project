package Project.ThisPlayer;

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
        boolean totalBudget = budget + amount >= 0;
        budget = Math.max(0, budget + amount);
        return totalBudget;
    }

    @Override
    public String toString(){
        return name;
    }
}
