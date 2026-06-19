import java.awt.*;

import javax.swing.JPanel;

public class MyDrawing 
{
    private int x, y, w, h, size;
    private Color lineColor = Color.black; 
    private Color fillColor = Color.white;
    private boolean isSelected = false;
    private Shape region;
    final int SIZE = 7;

    private int lineWidth;
    private boolean dashed = false;
    private boolean shadow = false;
    private boolean ishortdash = false;
    private boolean tripline = false;

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
        //this.dashed = d;
    }


    public MyDrawing(int x, int y, int w, int h, boolean d){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.dashed = d;
    }

 

    public MyDrawing(int x, int y, int w, int h, Color lineColor){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.lineColor = lineColor;
    }
    

    public void draw (Graphics g){
        if(isSelected){
            g.setColor(Color.black);
            g.drawRect(x+w/2-SIZE/2, y-SIZE/2, SIZE, SIZE);
            g.drawRect(x-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
            g.drawRect(x+w/2-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
            g.drawRect(x+w-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
            g.drawRect(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
            g.drawRect(x+w-SIZE/2, y-SIZE/2, SIZE, SIZE);
            g.drawRect(x-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
            g.drawRect(x+w-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
        }
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
        this.w = size;
        this.h = size;
    }

    public void setW(int w){
        this.w = w;
    }

    public void setH(int h){
        this.h = h;
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

    public void setShadow(boolean s){
        shadow = s;
    }

    public void setShortDashed(boolean d){
        this.ishortdash = d;
    }

    public void setSelected(boolean s){
        this.isSelected = s;
    }

    public boolean getSelected(){
        return isSelected;
    }


    public boolean getShortDashed(){
        return this.ishortdash;
    }
    
    public void setBold(boolean b){
        if (b){
            lineWidth = 3;
        }
        else{
            lineWidth = 1;
        }
    }

    public void setTripLine(boolean t){
        this.tripline = t;
    }

    public boolean getTripLine(){
        return this.tripline;
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

    public boolean getShadow(){
        return shadow;
    }


    //week3以降
    public boolean contains(int x, int y){
        if (region != null){
            return region.contains(x, y);
        }
        return false;
    }

    public void setRegion(){

    }
}

