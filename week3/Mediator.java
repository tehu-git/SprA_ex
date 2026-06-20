import java.util.Enumeration;
import java.util.Vector;
import java.awt.*;

public class Mediator {
    Vector<MyDrawing> drawings;
    MyCanvas canvas;
    MyDrawing selectedDrawing = null;

    public Mediator(MyCanvas canvas){
        this.canvas = canvas;
        drawings = new Vector<MyDrawing>();
    }

    public Enumeration<MyDrawing> drawingsElements(){
        return drawings.elements();
    }
     
    public void addDrawing(MyDrawing d){
        drawings.add(d);
        //setSelectedDrawing(d);
    }
    

    public void removeDrawing(MyDrawing d){
        drawings.remove(d);
    }

    public MyDrawing getSelectedDrawing(){
        return selectedDrawing;
    }

    public void move(int dx, int dy){
        if(selectedDrawing != null){
            selectedDrawing.move(dx, dy);
        }
    }

    public void repaint(){
        canvas.repaint();
    }

    public void clearSelected(){
        if(selectedDrawing != null){
            selectedDrawing.setSelected(false);
            selectedDrawing = null;
        }
    }

    public void setColor(Color c){
        if (selectedDrawing != null){
            selectedDrawing.setLineColor(c);
            selectedDrawing.setFillColor(c);
        }
    }

    public void setSelected(int x, int y){
        int size = drawings.size();
        MyDrawing found = null;
        for (int i = size -1; i >= 0; i--){
            MyDrawing d = drawings.get(i);
            if (d.contains(x, y)){
                found = d;
                break;
            }
        }
        if (found != null){
            if (found != selectedDrawing){
                clearSelected();
                selectedDrawing = found;
                selectedDrawing.setSelected(true);
                System.out.println("Selected: " + selectedDrawing);
            }
        }
        
        repaint();
    }
    
}
