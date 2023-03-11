package Project.GameProcess;
import Project.ThisPlayer.*;
import Project.ThisRegion.*;
import Project.parseEvaluator.nodes.*;
import java.util.*;

public interface Game {
    Player getPlayer1();
    Player getCurrentPlayer();
    Player getWin();

    List<Region> getTerritory();
    Region regionOn(Position position);
    Region cityCrewRegion();

    boolean attack(DirectionNode direction, long totalPay);
    boolean collect(long totalValue);
    boolean invest(long totalValue);
    boolean relocate();
    boolean move(DirectionNode direction);

    long opponent();
    long nearby(DirectionNode direction);
    long budget();
    long getTurn();

    void getConstructionPlan();
}
