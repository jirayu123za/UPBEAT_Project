package Project.parseEvaluator;
import Project.FileHelpRead;
import Project.Nodes.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

public class ProcessParseTest {
    public ProcessParse parser;
    public ExecuteNode execute;
    protected FileHelpRead fileHelpRead;

    @Test
    public void testReadFileConstructionPlan() {
        String filePath = "Back_end\\src\\constructionPlanCode.txt";
        try {
            String content = FileHelpRead.readFile(filePath);

            // Perform any additional validation, e.g., checking the number of nodes
            Assertions.assertNotNull(content, "Result should not be null");
            Assertions.assertFalse(content.isEmpty(), "Result should not be empty");
        } catch (IOException e) {
            Assertions.fail("Error reading the file: " + e.getMessage());
        } catch (RuntimeException e) {
            Assertions.fail("Exception occurred while reading the file: " + e.getMessage());
        }
    }

    @Test
    public void testReadFileThenParse() {
        String filePath = "Back_end\\src\\constructionPlanCode.txt";
        try {
            String content = FileHelpRead.readFile(filePath);
            GrammarTokenizer tokenizer = new GrammarTokenizer(content);
            ProcessParse parser = new ProcessParse(tokenizer);
            List<ExecuteNode> result = parser.parse();

            Assertions.assertNotNull(result, "Result should not be null");
            Assertions.assertFalse(result.isEmpty(), "Result should not be empty");
            //System.out.println(result);
        } catch (IOException e) {
            Assertions.fail("Error reading the file: " + e.getMessage());
        } catch (RuntimeException e) {
            Assertions.fail("Parsing error: " + e.getMessage());
        }
    }

    @Test
    public void testParseExpression() {
        String input = "1 + 2 * (3 - 4) / 5 ^ 6";
        GrammarTokenizer tokenizer = new GrammarTokenizer(input);
        ProcessParse parser = new ProcessParse(tokenizer);
        Assertions.assertThrows(SyntaxError.Command404.class, parser::parse);
    }

    @Test
    public void testParseWrongWord() {
        String input = "money";
        GrammarTokenizer tokenizer = new GrammarTokenizer(input);
        ProcessParse parser = new ProcessParse(tokenizer);
        Assertions.assertThrows(SyntaxError.Command404.class, parser::parse, "Expected a syntax error due to the wrong word");
    }

    @Test
    public void testParseReservedWord() {
        String input = "downleft";
        GrammarTokenizer tokenizer = new GrammarTokenizer(input);
        ProcessParse parser = new ProcessParse(tokenizer);
        Assertions.assertThrows(SyntaxError.ReservedWord.class, parser::parse);
    }
}
