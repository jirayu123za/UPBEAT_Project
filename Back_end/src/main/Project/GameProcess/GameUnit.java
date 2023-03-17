package Project.GameProcess;
import Project.Nodes.*;
import Project.parseEvaluator.*;
import java.util.*;

public class GameUnit {
    public Map<String, Long> evaluate(List<ExecuteNode> lists) {
        Map<String, Long> bindings = new HashMap<>();
        for(ExecuteNode list : lists){
            if(!(list instanceof AssignmentStatementNode)){
                throw new IllegalArgumentException("Invalid config");
            }
            ((AssignmentStatementNode) list).execute(bindings);
        }
        return bindings;
    }

    public static class ConfigurationParse extends ProcessParse{
        public ConfigurationParse(Tokenizer tkz) {
            super(tkz);
        }

        @Override
        public List<ExecuteNode> parse(){
            List<ExecuteNode> defaultValue = new ArrayList<>(10);
            parseStatements(defaultValue);
            return defaultValue;
        }
    }



    public Configuration loadConfiguration(String config){
        Parser parser = new ConfigurationParse(new GrammarTokenizer(config));
        List<ExecuteNode> lists = parser.parse();
        Map<String, Long> bindings = evaluate(lists);
        Configuration configuration = new Configuration() {
            @Override
            public long rows() {
                return bindings.getOrDefault("m", 50L);
            }

            @Override
            public long cols() {
                return bindings.getOrDefault("n", 50L);
            }

            @Override
            public long init_plan_min() {
                return bindings.getOrDefault("init_plan_min", 5L);
            }

            @Override
            public long init_plan_sec() {
                return bindings.getOrDefault("init_plan_sec", 0L);
            }

            @Override
            public long init_budget() {
                return bindings.getOrDefault("init_budget", 10000L);
            }

            @Override
            public long init_center_dep() {
                return bindings.getOrDefault("init_center_dep", 100L);
            }

            @Override
            public long plan_rev_min() {
                return bindings.getOrDefault("plan_rev_min", 50L);
            }

            @Override
            public long plan_rev_sec() {
                return bindings.getOrDefault("plan_rev_sec", 0L);
            }

            @Override
            public long rev_cost() {
                return bindings.getOrDefault("rev_cost", 100L);
            }

            @Override
            public long max_dep() {
                return bindings.getOrDefault("max_dep", 1000000L);
            }

            @Override
            public long interest_pct(long turn, long deposit) {
                return (long) (bindings.getOrDefault("interest_pct", 0L) * Math.log10(deposit) * Math.log(turn));
            }
        };
        if(configuration.init_plan_sec() >= 60) throw new IllegalArgumentException("Invalid config");
        if(configuration.plan_rev_sec() >= 60) throw new IllegalArgumentException("Invalid config");
        return configuration;
    }

    public Configuration defaultConfig(){
        return loadConfiguration("""
                m=4
                n=4
                init_plan_min=5
                init_plan_sec=0
                init_budget=10000
                init_center_dep=100
                plan_rev_min=50
                plan_rev_sec=0
                rev_cost=100
                max_dep=1000000
                interest_pct=5
                """);
    }

    // create new territory from given configuration
    // create new a player
    // create new game instance
    // create new game with specific configuration
}
