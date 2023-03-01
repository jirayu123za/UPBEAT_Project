package Project;

public class Region {
    // Variable
    protected long max_deposit;
    protected long interestRate_percentage;
    protected long init_budget;

    protected double deposit;
    protected int pos_xAxis;
    protected int pos_yAxis;
    protected String owner = null;
    protected boolean CityCenter = false;

    // Constructor
    public Region(long interestRate_percentage, long max_deposit,  long init_budget, int pos_xAxis, int pos_yAxis){
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

    public void invest(int investmentAmount, Player player){
        int totalCost = investmentAmount + 1;

        if(player.hasEnoughBudget(totalCost)){
            if(owner == null || owner.equals(player.getName()) || isAdjacentToPlayerRegion(player) ){
                int newDeposit = (int) Math.min(deposit + investmentAmount, max_deposit);
                deposit = newDeposit;
                player.decreaseBudget(totalCost);
            }
        }else{
            player.setOutOfBudget();
        }
    }

    public void collect(int collectionAmount, Player player){
        int totalCost = 1;

        if(player.hasEnoughBudget(totalCost)){
            if(collectionAmount <= deposit){
                deposit -= collectionAmount;
                player.decreaseBudget(totalCost);
            }
        }else{
            player.setOutOfBudget();
        }
    }

    public boolean isAdjacentToPlayerRegion(Player player){
        for(Region adjacentRegion : player.getAdjacentRegions(this.pos_xAxis, this.pos_yAxis)){
            if(Math.abs(adjacentRegion.pos_xAxis - this.pos_xAxis) <= 1 && Math.abs(adjacentRegion.pos_yAxis - this.pos_yAxis) <= 1) {
                if (adjacentRegion.owner != null && adjacentRegion.owner.equals(player.getName())) {
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

}
