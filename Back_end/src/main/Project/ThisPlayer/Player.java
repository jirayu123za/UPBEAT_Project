package Project.ThisPlayer;

import java.util.Map;

public interface Player {
    int getID();
    String getName();
    long getBudget();
    boolean updateBudget(long amount);
    Map<String, Long> identifiers();

}
