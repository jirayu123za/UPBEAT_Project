package Project.GameProcess;
import java.io.*;
import java.util.*;

public class Config {
    public static void readFile(String path){
        try (FileReader fr = new FileReader(path);
             Scanner s = new Scanner(fr)) {
             s.next();









        }
        catch (FileNotFoundException e) {
            System.out.println("File not found <- From Project.GameProcess.Config");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}