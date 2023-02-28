package Project;
import java.util.*;

public class Territory {
    // Variable
    protected int m,n;
    protected long max_deposit;
    protected long interestRate_percentage;
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
                Territory[i][j] = new Region(this.max_deposit, this.interestRate_percentage, i, j);
            }
        }
    }


}
