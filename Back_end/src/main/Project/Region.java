package Project;

public class Region {
    // Variable
    protected long max_deposit;
    protected long interestRate_percentage;

    protected double deposit;
    protected int pos_xAxis;
    protected int pos_yAxis;
    protected String owner = null;
    protected boolean CityCenter = false;

    // Constructor
    public Region(long interestRate_percentage, long max_deposit, int pos_xAxis, int pos_yAxis){
        this.pos_xAxis = pos_xAxis;
        this.pos_yAxis = pos_yAxis;
        this.interestRate_percentage = interestRate_percentage;
        this.max_deposit = max_deposit;
    }

    // method
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
        /**
         * need to implement
         * */
    }

    public void isOwner(String name){
        /**
         * need to implement
         * */
    }

    public void changeOwner(String owner){
        /**
         * need to implement
         * */
    }

    public void invest(){
        /**
         * need to implement
         * */
    }

    public boolean collect(){
        /**
         * need to implement
         * */
        return false;
    }

    public int returnDeposit(){
        /**
         * need to implement
         * */
        return 0;
    }

    public void updateDeposit(){
        /**
         * need to implement
         * */
    }

}
