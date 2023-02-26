package Project;

import java.util.*;

public class CityCrew {
    protected Map<String, Double> variables;
    protected boolean didActionCommand = false;
    protected long opponentLocation;
    protected long deposit;
    protected long budget;
    protected double[][] territory;
    protected int row, col;
    protected int[] location;

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

    public long getBudget() {
        return this.budget;
    }

    public void investDeposit(long deposit) {
        if(deposit > this.budget){
            throw new IllegalArgumentException("Cannot invest more than budget");
        }
        this.budget -= deposit;
        this.deposit += deposit;
    }

    public boolean relocate(String direction) {
        // Get the current position of the city center
        int[] currentPosition = this.getLocation();

        // Calculate the new position based on the direction
        int[] newPosition;
        switch (direction) {
            case "up":
                newPosition = new int[]{currentPosition[0] - 1, currentPosition[1]};
                break;
            case "down":
                newPosition = new int[]{currentPosition[0] + 1, currentPosition[1]};
                break;
            case "left":
                newPosition = new int[]{currentPosition[0], currentPosition[1] - 1};
                break;
            case "right":
                newPosition = new int[]{currentPosition[0], currentPosition[1] + 1};
                break;
            default:
                // Invalid direction
                return false;
        }

        // Check if the new position is within the territory
        if (newPosition[0] < 0 || newPosition[0] >= territory.length ||
                newPosition[1] < 0 || newPosition[1] >= territory[0].length) {
            return false;
        }

        // Update the city center location
        this.setLocation(newPosition);

        return true;
    }

    public int[] getLocation() {
        return new int[]{this.row, this.col};
    }

    public void setLocation(int[] newPosition) {
        this.location = newPosition;
    }
}
