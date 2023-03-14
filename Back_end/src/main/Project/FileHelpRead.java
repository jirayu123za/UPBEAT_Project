package Project;
import java.io.*;
import java.nio.file.*;

public class FileHelpRead {
    public static String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }
}
