package Project.GameProcess;
import Project.ThisRegion.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class TestForGameUnit {
        @Test
        public void testCheckLoadConfiguration() {
            Assertions.assertDoesNotThrow(() -> GameUnit.loadConfiguration("""
                rows = 50
                cols = 50
                init_plan_min = 1
                init_plan_sec = 1
                init_budget = 5000
                init_center_dep = 1000
                plan_rev_min = 1
                plan_rev_sec = 1
                rev_cost = 50
                max_dep = 200000
                interest_pct = 500
                """));

            Assertions.assertDoesNotThrow(() -> GameUnit.loadConfiguration("""
                rows = 50
                cols = 50
                init_budget = 5000
                init_center_dep = 1000
                rev_cost = 50
                max_dep = 200000
                interest_pct = 500
                """));

            Assertions.assertThrows(GameException.InvalidConfiguration.class, () -> GameUnit.loadConfiguration("""
                plan_rev_sec=60
                """));
        }

        @Test
        public void testCityCenter() {
            Game game = GameUnit.createGame("Player1", "Player2");
            List<Region> territory = game.getTerritory();
            List<Region> cityCenters = new ArrayList<>(2);

            for (Region region : territory) {
                if (region.isCityCenter()) cityCenters.add(region);
            }

            Assertions.assertEquals(2, cityCenters.size(), "more than two city centers created");
            Assertions.assertNotEquals(cityCenters.get(0), cityCenters.get(1), "city center collapse");
        }

        @Test
        public void testRegionLocation() {
            Game game = GameUnit.createGame("Player1", "Player2");
            List<Region> territory = game.getTerritory();
            Set<Position> regions = new HashSet<>();

            for (Region region : territory) {
                regions.add(region.getLocation());
            }

            Assertions.assertEquals(regions.size(), territory.size(), "Duplicated Region");
        }

        @Test
        public void createCustomGame() {
            Game game1 = GameUnit.createCustom("""
                rows=9999
                cols=9999
                """, "player1", "player2");

            Assertions.assertEquals(999*999, game1.getTerritory().size());
        }
}