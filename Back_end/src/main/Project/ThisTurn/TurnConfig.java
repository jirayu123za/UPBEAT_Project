package Project.ThisTurn;
import Project.GameProcess.Configuration;
import Project.ThisPlayer.*;
import Project.ThisRegion.*;
import java.util.*;

public class TurnConfig implements Turn{
    protected long turn;
    protected Player player1;
    protected Player currentPlayer;
    protected Map<Player, Region> cityCenterOfRegion;
    protected Configuration config;
    protected Region cityCrew;
    protected List<Region> territory;


    // Constructor
    public TurnConfig(long turn){
        this.turn = turn;
    }

    @Override
    public void beingTurn() {
        getCityCenters();
        cityCrew = cityCenterOfRegion.get(currentPlayer);
    }

    @Override
    public void endTurn() {
        if(currentPlayer == player1){
            //currentPlayer == nextPlayer;
        }else{
            currentPlayer = player1;
            interestProcess();
            updateTurn();
        }
    }

    @Override
    public void updateTurn() {
        turn++;
    }

    @Override
    public long getTurn() {
        return this.turn;
    }

    // method
    public void getCityCenters() {
        for (Region region : territory) {
            if (region.isCityCenter())
                cityCenterOfRegion.put(region.getOwner(), region);
        }
    }

    public void interestProcess() {
        for (Region region : territory) {
            if (region.getOwner() != null) {
                long deposit = region.getDeposit();
                deposit *= config.interest_pct(turn, deposit) / 100.0;
                region.updateDeposit(deposit);
            }
        }
    }
}
