import java.awt.Canvas;

public class StateManager {
    private State nowState;
    private MyCanvas canvas; 
    private Mediator mediator;
    private int dashmode;
    private boolean shadow;
    //private boolean ishortdash;
    private boolean bold;
    private boolean tripline;

    public StateManager(MyCanvas canvas){
        this.canvas = canvas;
        this.mediator = canvas.getMediator();
        this.nowState = null;
        this.dashmode = 0;
        this.shadow = false;
        //this.ishortdash = false;
        this.bold = false;
        this.tripline = false;
    }

    public void setState(State s){
        nowState = s;
    }

    public void setDashmode(int mode){
        this.dashmode = mode;
        if (mediator.getSelectedDrawings() != null){
            for (MyDrawing d : mediator.getSelectedDrawings()) {
                d.setDashmode(mode);
            }
        }
        mediator.repaint();
    }

    public void setShadow(boolean s){
        this.shadow = s;
        if (mediator.getSelectedDrawings() != null){
            for (MyDrawing d : mediator.getSelectedDrawings()) {
                d.setShadow(s);
            }
        }
        mediator.repaint();
    }

    public int getDashmode(){
        return this.dashmode;
    }

    public boolean getshadow(){
        return this.shadow;
    }

    /*
    public void setShortDashed(boolean d){
        this.ishortdash = d;
    }

     public boolean getShortDashed(){
        return this.ishortdash;
    }
    */
    public void setBold(boolean b){
        this.bold = b;
        if (mediator.getSelectedDrawings() != null){
            for (MyDrawing d : mediator.getSelectedDrawings()) {
                d.setBold(b);
            }
        }
        mediator.repaint();
    }

    public boolean getBold(){
        return this.bold;
    }

    public void setTripLine(boolean t){
        this.tripline = t;
    }

    public boolean getTripLine(){
        return this.tripline;
    }


    public Mediator getMediator(){
        return canvas.getMediator();
    }

    public void addDrawing(MyDrawing d){
        canvas.getMediator().addDrawing(d);
    }

    public void mouseDown(int x, int y){
        if (nowState != null){
            nowState.mouseDown(x, y);
        }
    }

    public void mouseDrag(int x, int y){
        if (nowState != null){
            nowState.mouseDrag(x, y);
        }
    }

    public void mouseUp(int x, int y){
        if (nowState != null){
            nowState.mouseUp(x, y);
        }
       mediator.saveState(); // Save the state after mouse up
    }
}
