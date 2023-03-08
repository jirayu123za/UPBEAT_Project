package Project.ThisRegion;

import Project.ThisPlayer.Player;

public interface Region {
    Player getOwner();

    boolean isCityCenter();

    long getDeposit();

    void updateOwner(Player owner);
    void updateDeposit(long amount);
    void setCityCenter(Player owner);
}
