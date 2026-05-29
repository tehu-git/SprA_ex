import java.awt.*;

import javax.swing.JPanel;

public class MyDrawing 
{
    private int x, y, w, h;
    private Color lineColor, fillColor;
    private int lineWidth;

    public MyDrawing(){
        x = y = 0;
        w = 40;
        h = 40;
        lineColor = Color.black;
        fillColor = Color.white;
        lineWidth = 1;

    }
    

    public void draw (Graphics g){

    }

    public void move(int dx, int dy){
        x += dx;
        y += dy; 
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setSize(int w, int h){
        w = w *2;
        h = h *2;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getW(){
        return w;
    }

    public int getH(){
        return h;
    }

    public Color getFillColor(){
        return fillColor;
    }

    public Color getLineColor(){
        return lineColor;
    }

    public int getLineWidth(){
        return lineWidth;
    }
}