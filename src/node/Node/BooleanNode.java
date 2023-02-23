public class BooleanNode implements Node{
    protected boolean value;

    public BooleanNode(boolean value){
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value ? 1 : 0;
    }

    @Override
    public void print(int height) {

    }
}
