package Project;

public class Time {
    protected double deltaTime;
    protected double fps;

    public Time(){
        this.deltaTime = 1.0;
        this.fps = 60.0;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public double getFps() {
        return fps;
    }
}
