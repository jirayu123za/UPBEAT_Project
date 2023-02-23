public class RelocateCommandNode implements Node{
    protected CityCrew city;
    protected String direction;

    public RelocateCommandNode(CityCrew city, String direction){
        this.city = city;
        this.direction = direction;
    }

    @Override
    public double evaluate() {
        city.relocate(direction);
        return 0;
    }

    @Override
    public void print(int height) {

    }
}
