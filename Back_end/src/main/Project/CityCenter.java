package Project;

public class CityCenter {
    // variable
    protected int centerRow;
    protected int centerCol;
    protected long init_center_deposit;

    // constructor
    public CityCenter(long centerRow, long centerCol, long init_center_deposit){
        this.centerRow = (int) centerRow;
        this.centerCol = (int) centerCol;
        this.init_center_deposit = init_center_deposit;
    }

    // method
    // Getters and setters
    public int getCenterRow() {
        return centerRow;
    }

    public void setCenterRow(int centerRow) {
        this.centerRow = centerRow;
    }

    public int getCenterCol() {
        return centerCol;
    }

    public void setCenterCol(int centerCol) {
        this.centerCol = centerCol;
    }

    public long getDeposit() {
        return init_center_deposit;
    }

    public void setDeposit(long deposit) {
        this.init_center_deposit = deposit;
    }
}
