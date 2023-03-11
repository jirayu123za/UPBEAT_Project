package Project.ThisRegion;
import Project.ThisPlayer.*;

public class RegionConfig implements Region{
    protected Player owner;
    protected Position location;
    protected boolean isCityCenter;
    protected long max_Deposit;
    protected long deposit;

    public RegionConfig(Position location, long max_Deposit){
        this.location = location;
        this.max_Deposit = max_Deposit;
        this.owner = null;
        this.isCityCenter = false;
        this.deposit = 0;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public void updateOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public boolean isCityCenter() {
        return isCityCenter;
    }

    @Override
    public void setCityCenter(Player owner){
        this.isCityCenter = true;
        this.owner = owner;
    }

    @Override
    public void changeCityCenter() {
        isCityCenter = false;
    }

    @Override
    public Position getLocation() {
        return this.location;
    }

    @Override
    public long getDeposit() {
        return deposit;
    }

    @Override
    public void updateDeposit(long amount) {
        deposit = Math.max(0, amount + deposit);
        deposit = Math.min(max_Deposit, deposit);
    }

    /*
    protected long max_deposit;
    protected long interestRate_percentage;
    protected long init_budget;

    protected double deposit;
    protected int pos_xAxis;
    protected int pos_yAxis;
    protected String owner = null;
    protected boolean CityCenter = false;

    // Constructor
    public RegionConfig(long interestRate_percentage, long max_deposit, long init_budget, int pos_xAxis, int pos_yAxis){
        this.pos_xAxis = pos_xAxis;
        this.pos_yAxis = pos_yAxis;
        this.interestRate_percentage = interestRate_percentage;
        this.max_deposit = max_deposit;
        this.init_budget = init_budget;
    }

    // method
    public int getPos_xAxis(){
        return pos_xAxis;
    }

    public int getPos_yAxis(){
        return pos_yAxis;
    }

    public long Int(int turn){
        return (long) (this.interestRate_percentage*(Math.log10(deposit))*Math.log(turn));
    }

    public boolean isPossessionLost(){
        return true;
    }

    public boolean isShoot(int totalDamage){
        this.deposit -= totalDamage;
        if(Math.floor(deposit) > 0){
            return false;
        }else{
            isPossessionLost();
            return true;
        }
    }

    public boolean isCityCenter(){
        return CityCenter;
    }

    public void changeCityCenter(boolean change){
        this.CityCenter = change;
    }

    public boolean isOwner(String name){
        return owner != null && owner.equals(name);
    }

    public void changeOwner(String owner){
        this.owner = owner;
    }

    public void invest(int investmentAmount, PlayerConfig playerConfig){
        int totalCost = investmentAmount + 1;

        if(playerConfig.hasEnoughBudget(totalCost)){
            if(owner == null || owner.equals(playerConfig.getName()) || isAdjacentToPlayerRegion(playerConfig) ){
                int newDeposit = (int) Math.min(deposit + investmentAmount, max_deposit);
                deposit = newDeposit;
                playerConfig.decreaseBudget(totalCost);
            }
        }else{
            playerConfig.setOutOfBudget();
        }
    }

    public void collect(int collectionAmount, PlayerConfig playerConfig){
        int totalCost = 1;

        if(playerConfig.hasEnoughBudget(totalCost)){
            if(collectionAmount <= deposit){
                deposit -= collectionAmount;
                playerConfig.decreaseBudget(totalCost);
            }
        }else{
            playerConfig.setOutOfBudget();
        }
    }

    public boolean isAdjacentToPlayerRegion(PlayerConfig playerConfig){
        for(RegionConfig adjacentRegionConfig : playerConfig.getAdjacentRegions(this.pos_xAxis, this.pos_yAxis)){
            if(Math.abs(adjacentRegionConfig.pos_xAxis - this.pos_xAxis) <= 1 && Math.abs(adjacentRegionConfig.pos_yAxis - this.pos_yAxis) <= 1) {
                if (adjacentRegionConfig.owner != null && adjacentRegionConfig.owner.equals(playerConfig.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int returnDeposit(String name){
        if(isOwner(name)){
            return (int) Math.floor(deposit);
        }else{
            return ((int) Math.floor(deposit))*-1;
        }
    }

    public void updateDeposit(int turn){
        interestRate_percentage = Int (turn);
        this.deposit += this.deposit*interestRate_percentage/100;
    }
*/
}
