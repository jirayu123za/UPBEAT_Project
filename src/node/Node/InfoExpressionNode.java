public class InfoExpressionNode implements Node{
    protected String info;
    protected CityCrew city;

    public InfoExpressionNode(String info, CityCrew city){
        this.info = info;
        this.city = city;
    }

    @Override
    public double evaluate() {
        if (info.equals("opponent")){
            return city.getOpponentLocation();
        }else {
            return city.getTotalDeposit();
        }
    }

    @Override
    public void print(int height) {

    }
}
