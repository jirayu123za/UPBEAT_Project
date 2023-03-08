package Project.ThisPlayer;

public interface Player {
    int getID();
    String getName();
    long getBudget();
    boolean updateBudget(long amount);

}
