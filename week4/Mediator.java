import java.util.Enumeration;
import java.util.Vector;
import java.awt.*;

public class Mediator {
    Vector<MyDrawing> drawings;
    MyCanvas canvas;
    Vector<MyDrawing> selectedDrawings = new Vector<MyDrawing>();
    Vector<MyDrawing> buffer = null;

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

    public Vector<MyDrawing> getSelectedDrawings(){
        return selectedDrawings;
    }

    public void move(int dx, int dy){
        if(selectedDrawings != null){
            for (MyDrawing d : selectedDrawings){
                d.move(dx, dy);
            }
        }
    }

    public void clearbuffer(){
        buffer = null;
    }

    public void copy(){
        clearbuffer();
        buffer = new Vector<MyDrawing>();
        for (MyDrawing d: selectedDrawings){
            buffer.add(d.clone());
        }
    }

    public void cut(){
        clearbuffer();
        buffer = new Vector<MyDrawing>();
        for (MyDrawing d: selectedDrawings){
            buffer.add(d.clone());
        }
        
        for (MyDrawing d : selectedDrawings) {
            removeDrawing(d);
        }
        canvas.repaint();
    }

    public void paste(){
        if(buffer != null){
            Vector<MyDrawing> pasteDrawings = new Vector<MyDrawing>();
            for (MyDrawing d : buffer){
                d.clone();
                d.move(10, 10);
                addDrawing(d);
                pasteDrawings.add(d);
            }
            repaint();
        }
    }

    public void repaint(){
        canvas.repaint();
    }

    public void clearSelected(){
        if(selectedDrawings != null){
            for (MyDrawing d : selectedDrawings){
                d.setSelected(false);
            }
            selectedDrawings.clear();
        }
    }

    public void setColor(Color c){
        if (selectedDrawings != null){
            for (MyDrawing d : selectedDrawings){
                d.setFillColor(c);
            }
        }
    }

    public void setLineColor(Color c){
        if (selectedDrawings != null){
            for (MyDrawing d : selectedDrawings){
                d.setLineColor(c);
            }
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
        //
        if (found != null){
            if (!selectedDrawings.contains(found)){
                clearSelected();
                selectedDrawings.add(found);
                found.setSelected(true);
                System.out.println("Selected: " + found);
            }
        } else {
            clearSelected();
            //System.out.println("Selected: null");
        }
        
        repaint();
    }

    public void RectsetSelected(MyDrawing setRect){
        clearSelected();
        Rectangle srect = new Rectangle(setRect.getX(), setRect.getY(), setRect.getW(), setRect.getH());

        for (MyDrawing d : drawings) {
            if (d == setRect) {
                continue;
            }
            if(srect.intersects(d.getRegion())){
                selectedDrawings.add(d);
                d.setSelected(true);
            }
        }
    }
    
}
