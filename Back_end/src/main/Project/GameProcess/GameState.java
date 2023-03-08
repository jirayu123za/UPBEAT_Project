package Project.GameProcess;
import Project.ThisPlayer.PlayerConfig;

public class GameState implements GameConfig{
    @Override
    public PlayerConfig getPlayer() {
        return null;
    }

    @Override
    public PlayerConfig getCurrentPlayer() {
        return null;
    }

    @Override
    public void getConstructionPlan() {

    }

    @Override
    public boolean attack() {
        return false;
    }

    @Override
    public boolean collect() {
        return false;
    }

    @Override
    public boolean invest() {
        return false;
    }

    @Override
    public boolean relocate() {
        return false;
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public long opponent() {
        return 0;
    }

    @Override
    public long nearby() {
        return 0;
    }

    @Override
    public long budget() {
        return 0;
    }
}
