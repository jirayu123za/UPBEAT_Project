package Project.ThisRegion;
import Project.Nodes.*;
import org.junit.jupiter.api.*;

public class TestPosition2D {
    protected Position position,
                       up,
                       upright,
                       upleft,
                       down,
                       downright,
                       downleft;

    @BeforeEach
    public void setUp() {
        position = Position.of(2, 1);
        up = Position.of(2, 0);
        upright = Position.of(3, 1);
        upleft = Position.of(1, 1);
        down = Position.of(2, 2);
        downright = Position.of(3, 2);
        downleft = Position.of(1, 2);
    }

    @Test
    public void testCreation(){
        Assertions.assertEquals(2, position.getPosX());
        Assertions.assertEquals(2, position.getPosY());
    }

    @Test
    public void testEquality(){
        Position samePosition = Position.of(2, 2);
        Assertions.assertEquals(position, samePosition);
        Position differentPosition = Position.of(3, 3);
        Assertions.assertNotEquals(position, differentPosition);
    }

    @Test
    public void testDirection(){
        Assertions.assertEquals(up, position.direction(DirectionNode.up));
        Assertions.assertEquals(upright, position.direction(DirectionNode.upright));
        Assertions.assertEquals(upleft, position.direction(DirectionNode.upleft));
        Assertions.assertEquals(down, position.direction(DirectionNode.down));
        Assertions.assertEquals(downright, position.direction(DirectionNode.downright));
        Assertions.assertEquals(downleft, position.direction(DirectionNode.downleft));
    }

    @Test
    public void testIsValidPosition(){
        long rows = 5;
        long cols = 5;

        // Test for a position inside the grid boundaries
        Position insideGrid = Position.of(2, 2);
        Assertions.assertTrue(insideGrid.Check_isValidPosition(rows, cols));

        // Test for a position with negative coordinates
        Position negativeCoordinates = Position.of(-1, -1);
        Assertions.assertFalse(negativeCoordinates.Check_isValidPosition(rows, cols));

        // Test for a position with coordinates exceeding the grid size
        Position outOfBounds = Position.of(6, 6);
        Assertions.assertFalse(outOfBounds.Check_isValidPosition(rows, cols));

        // Test for a position on the grid boundaries
        Position onBoundary = Position.of(4, 4);
        Assertions.assertTrue(onBoundary.Check_isValidPosition(rows, cols));
    }
}
