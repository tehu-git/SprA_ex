import java.awt.Canvas;

public class StateManager {
    private State nowState;
    private MyCanvas canvas; 
    private boolean dashed;

    public StateManager(MyCanvas canvas){
        this.canvas = canvas;
        nowState = null;
        dashed = false;
    }

    public void setState(State s){
        nowState = s;
    }

    public void setDashed(boolean d){
        dashed = d;
    }

    public void addDrawing(MyDrawing d){
        canvas.addDrawing(d);
    }

    public void mouseDown(int x, int y){
        if (nowState != null){
            nowState.mouseDown(x, y);
        }
    }
}
