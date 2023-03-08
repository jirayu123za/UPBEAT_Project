package Project.ThisRegion;

import Project.ThisRegion.Region;

public class Territory {
    // Variable
    protected int m,n;
    protected long max_deposit;
    protected long interestRate_percentage;
    protected long init_budget;
    protected Region[][] Territory = null;

    // Constructor
    public Territory(long m, long n, long max_deposit, long interestRate_percentage){
        this.m = (int) m;
        this.n = (int) n;
        this.max_deposit = max_deposit;
        this.interestRate_percentage = interestRate_percentage;
        Territory = new Region[this.m][this.n];

        for (int i = 0; i < m; i++){
            for (int j = 0 ;j < n; j++){
                Territory[i][j] = new Region(this.max_deposit, this.interestRate_percentage, init_budget, i, j);
            }
        }
    }

    // method
    public Region getRegion(int i, int j){
        return Territory[i][j];
    }

    public int getRows(){
        return m;
    }

    public int getCols(){
        return n;
    }

}
