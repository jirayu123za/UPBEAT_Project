import java.util.*;

public class CityCrew {
    protected Map<String, Double> variables;
    protected boolean didActionCommand = false;
    protected long opponentLocation;

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
}
