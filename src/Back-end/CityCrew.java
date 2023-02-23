import java.util.*;

public class CityCrew {
    protected Map<String, Double> variables;
    protected boolean didActionCommand = false;
    protected long opponentLocation;
    protected long deposit;

    public CityCrew(){
        variables = new HashMap<>();

    }

    public void move(String direction){

    }

    public void shoot(String direction) {
    }

    public void evaluate(){

    }

    public boolean getDidActionCommand(){
        return didActionCommand;
    }

    public void setDidActionCommand(boolean state){
        this.didActionCommand = state;
    }

    public void setOpponentLocation(long location) {
        this.opponentLocation = location;
    }

    public long getOpponentLocation() {
        return this.opponentLocation;
    }

    public int getNearbyDeposit(String direction) {
        int deposit = 0;
        /*Not complete*/
        return deposit;
    }

    public long getTotalDeposit() {
        long totalDeposit = 0;
        /*Not complete*/
        return totalDeposit;
    }

    public void collectDeposit(long deposit) {
        this.deposit += deposit;
    }
}
