package Project;
import java.util.*;

public class TimeMange {
    protected double deltaTime;
    protected double fps;

    public TimeMange(){
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
