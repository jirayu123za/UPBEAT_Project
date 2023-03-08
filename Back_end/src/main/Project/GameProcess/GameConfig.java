package Project.GameProcess;
import Project.ThisPlayer.PlayerConfig;

public interface GameConfig {
    PlayerConfig getPlayer();
    PlayerConfig getCurrentPlayer();

    void getConstructionPlan();

    boolean attack();
    boolean collect();
    boolean invest();
    boolean relocate();
    boolean move();

    long opponent();
    long nearby();
    long budget();
}
