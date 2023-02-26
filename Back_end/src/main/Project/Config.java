package Project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Config {
    static int m;
    static int n;
    static int init_plan_min;
    static int init_plan_sec;
    static long init_budget;
    static long init_center_dep;
    static int plan_rev_min;
    static int plan_rev_sec;
    static long rev_cost;
    static long max_dep;
    static long interest_pct;

    public static void readFile(String path){
        try (FileReader fr = new FileReader(path);
             Scanner s = new Scanner(fr)) {
             s.next();









        }
        catch (FileNotFoundException e) {
            System.out.println("File not found <- From Project.Config");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
