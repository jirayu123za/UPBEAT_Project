import java.util.*;

public class CityCrew {
    protected Map<String, Double> variables;
    protected boolean didActionCommand = false;
    protected boolean didOpponentLocation = false;
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


    public boolean getOpponentLocation() {
        return didOpponentLocation;
    }
}
