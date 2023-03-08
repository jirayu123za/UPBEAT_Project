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
}
