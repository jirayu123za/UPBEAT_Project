package Project.parseEvaluator.nodes;

public class DoneCommandNode implements Node{

    public DoneCommandNode(){
    }

    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public void print(int height) {
        for(int i = 0 ; i < height; ++i){
            System.out.print("   ");
        }
        System.out.println(" |---Done ");
    }
}
