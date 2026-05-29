import java.awt.*;

import javax.swing.JPanel;

public class MyDrawing 
{
    private int x, y, w, h, size;
    private Color lineColor, fillColor;
    private int lineWidth;
    private boolean dashed = false;

    public MyDrawing(){
        x = y = 0;
        w = 40;
        h = 40;
        lineColor = Color.black;
        fillColor = Color.white;
        lineWidth = 1;
    }


    public MyDrawing(int x, int y){
        this.x = x;
        this.y = y;
        w = 40;
        h = 40;
    }

    public MyDrawing(int x, int y, boolean d){
        this.x = x;
        this.y = y;
        w = 40;
        h = 40;
        this.dashed = d;
    }

    public MyDrawing(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

 

    public MyDrawing(int x, int y, int w, int h, Color lineColor){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.lineColor = lineColor;
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

    public void setSize(int size){
        this.size = size;
    }

    public void setLineColor(Color lineColor){
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    public void setLineWidth(int lineWidth){
        this.lineWidth = lineWidth;
    }

    public void setDashed(boolean b){
        dashed = b;
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

    public int setSize(){
        return size;
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

    public boolean getDashed(){
        return dashed;
    }
}