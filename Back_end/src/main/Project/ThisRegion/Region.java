package Project.ThisRegion;
import Project.ThisPlayer.*;

public interface Region {
    Player getOwner();

    Position getLocation();

    boolean isCityCenter();

    long getDeposit();

    void changeCityCenter();
    void updateOwner(Player owner);
    void updateDeposit(long amount);
    void setCityCenter(Player owner);
}
