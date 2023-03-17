package Project.GameProcess;
import Project.ThisPlayer.Player;
import Project.ThisRegion.Position;
import Project.ThisRegion.PositionConfig;
import Project.ThisRegion.Region;
import org.junit.jupiter.api.*;
import java.util.*;

public class TestForGame {
    protected GameConfig game;
    protected List<SampleRegion> territory;
    protected SamplePlayer player1,player2;

    public static abstract class SampleRegion implements Region{
        public long deposit = 0;
        public Player owner = null;
    }

    public abstract static class SamplePlayer implements Player{
        public Map<String, Long> identifiers = new HashMap<>();
        public SampleRegion cityCenter;
        public long budget = 1;

        public SamplePlayer(SampleRegion cityCenter){
            cityCenter.updateOwner(this);
            this.cityCenter = cityCenter;
        }
    }

    protected SampleRegion mock(Position loc, long maxDeposit){
        return new SampleRegion() {
            private boolean isCityCenter;

            @Override
            public Player getOwner() {
                return owner;
            }

            @Override
            public boolean isCityCenter() {
                return isCityCenter;
            }

            @Override
            public void changeCityCenter() {
                isCityCenter = false;
            }

            @Override
            public Position getLocation() {
                return loc;
            }

            @Override
            public long getDeposit() {
                return deposit;
            }

            @Override
            public void updateOwner(Player owner) {
                this.owner = owner;
            }

            @Override
            public void updateDeposit(long amount) {
                deposit = Math.max(0, deposit + amount);
                deposit = Math.min(maxDeposit, deposit);
            }

            @Override
            public void setCityCenter(Player owner) {
                isCityCenter = true;
                updateOwner(owner);
            }
        };
    }

    protected List<SampleRegion> mock(int rows, int cols, long maxDeposit) {
        List<SampleRegion> regions = new ArrayList<>(rows * cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Position loc = new PositionConfig(j, i);
                regions.add(mock(loc, maxDeposit));
            }
        }
        return regions;
    }

    protected SamplePlayer mock(SampleRegion defineCenterLoc){
        SamplePlayer player = new SamplePlayer(defineCenterLoc) {
            @Override
            public int getID() {
                // need modify custom throw exception
                return 0;
            }

            @Override
            public String getName() {
                // need modify custom throw exception
                return null;
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
            public Map<String, Long> identifiers() {
                return identifiers;
            }
        };
        defineCenterLoc.setCityCenter(player);
        return player;
    }

    // need modify on GameUnit
    protected Configuration mock(){
        return null;
    }

    @BeforeEach
    public void setBefore(){
        Configuration config = mock();
        territory = mock(10,10,config.max_dep());
        player1 = mock(territory.get(10));
        player2 = mock(territory.get(5));
        List<Region> regionList = new ArrayList<>(territory.size());
        regionList.addAll(territory);
        game = new GameConfig(player1, player2, config, regionList);
        game.turn.StartTurn();
    }

    @AfterEach
    public void setAfter(){
        game.turn.endTurn();
    }

    @Test
    public void testAttack(){

    }

    @Test
    public void testCollect(){

    }

    @Test
    public void testInvest(){

    }

    @Test
    public void testRelocate(){

    }

    @Test
    public void testMove(){

    }

    @Test
    public void testNearby(){

    }

    @Test
    public void testInterestPercentage(){

    }

    @Test
    public void lost_ByBudget(){

    }

    @Test
    public void lost_byCityCenter(){

    }

}
