package Project;
import java.util.*;
import java.io.*;

public class ConstructionPlanCombiner {

    public ConstructionPlanCombiner(){
    }
    // method to combine multiple files into a single string
    public static String combineFiles(String[] fileNames){
        StringBuilder sb = new StringBuilder();

        for(String fileName : fileNames){
            try (FileReader fileReader = new FileReader(fileName)) {
                Scanner scanner = new Scanner(fileReader);
                while (scanner.hasNextLine()) {
                    sb.append(scanner.nextLine());
                    sb.append("\n");
                }
            } catch (FileNotFoundException | NullPointerException e) {
                System.out.println("File not found: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
