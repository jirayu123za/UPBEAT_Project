package Project.ThisRegion;

public class PositionConfig implements Position{
    protected long x, y;

    public PositionConfig(long x, long y){
        this.x = x;
        this.y = y;
    }

    @Override
    public long getPosX() {
        return x;
    }

    @Override
    public long getPosY() {
        return y;
    }

    @Override
    public boolean equals(Object point){
        if(!(point instanceof Position position)){
            return false;
        }
        return position.getPosX() == x && position.getPosY() == y;
    }
}
