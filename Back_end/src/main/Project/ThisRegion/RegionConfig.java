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

    @Override
    public String toString(){
        return String.format("Owner : %s, location: %s", owner, location);
    }
}
