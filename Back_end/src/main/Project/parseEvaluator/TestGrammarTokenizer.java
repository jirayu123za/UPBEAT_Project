package Project.parseEvaluator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGrammarTokenizer {
    protected GrammarTokenizer test;
    @Test
    public void TestHasNextToken(){
        test = new GrammarTokenizer("move");
        Assertions.assertTrue(test.hasNextToken());

        test = new GrammarTokenizer(null);
        Assertions.assertFalse(test.hasNextToken());

        test = new GrammarTokenizer("");
        Assertions.assertFalse(test.hasNextToken());
    }

    @Test
    public void TestStringPeek(){
        test = new GrammarTokenizer("move");
        Assertions.assertTrue(test.peek("move"));
        Assertions.assertFalse(test.peek("m"));
        Assertions.assertFalse(test.peek(""));

        test = new GrammarTokenizer(null);
        Assertions.assertFalse(test.peek("move"));
        Assertions.assertFalse(test.peek("m"));
        Assertions.assertFalse(test.peek(""));

        test = new GrammarTokenizer("");
        Assertions.assertFalse(test.peek("move"));
        Assertions.assertFalse(test.peek("m"));
        Assertions.assertFalse(test.peek(""));
    }

    @Test
    public void TestPeek(){
        test = new GrammarTokenizer("move");
        Assertions.assertEquals("move",test.peek());

        test = new GrammarTokenizer(null);
        Assertions.assertThrows(IllegalArgumentException.class, test::peek);

        test = new GrammarTokenizer("");
        Assertions.assertThrows(IllegalArgumentException.class, test::peek);
    }

    @Test
    public void TestConsume(){
        test = new GrammarTokenizer("move");
        Assertions.assertEquals("move", test.consume());

        test = new GrammarTokenizer(null);
        Assertions.assertThrows(IllegalArgumentException.class, test::consume);

        test = new GrammarTokenizer("");
        Assertions.assertThrows(IllegalArgumentException.class, test::consume);
    }

    @Test
    public void TestStringConsume(){
        test = new GrammarTokenizer("move");
        Assertions.assertFalse(test.consume("m"));
        Assertions.assertDoesNotThrow(()-> test.consume("m"));
        Assertions.assertTrue(test.consume("move"));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> test.consume("move"));

        test = new GrammarTokenizer(null);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> test.consume(""));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> test.consume("m"));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> test.consume("move"));

        test = new GrammarTokenizer("");
        Assertions.assertThrows(IllegalArgumentException.class, ()-> test.consume(""));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> test.consume("m"));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> test.consume("move"));
    }

    @Test
    public void TestGetNewline(){
        test = new GrammarTokenizer("move");
        Assertions.assertEquals(1, test.getNewline());
        test.consume();

        Assertions.assertEquals(1, test.getNewline());
        test = new GrammarTokenizer(null);
        Assertions.assertEquals(1, test.getNewline());
        Assertions.assertFalse(test.hasNextToken());

        test = new GrammarTokenizer("");
        Assertions.assertEquals(1, test.getNewline());
        Assertions.assertFalse(test.hasNextToken());
    }

    @Test
    public void TestComputeNext(){
        // Don't need
    }

    @Test
    public void TestSkipComment(){
        // Don't need
    }

    @Test
    public void TestSkipCharacter(){
        // Don't need
    }

    @Test
    public void TestSkipLetter(){
        // Don't need
    }
}
