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
     
    public Vector<MyDrawing> getDrawings(){
        return drawings;
    }

    public void setDrawings(Vector<MyDrawing> loadedDrawings){
        this.drawings = loadedDrawings;
        clearSelected();
        repaint();
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
                MyDrawing cloned = d.clone();
                cloned.move(10, 10);
                addDrawing(cloned);
                pasteDrawings.add(cloned);
            }
            buffer = pasteDrawings;
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

    public void RectsetSelected(int sx, int sy, int sw, int sh){
        clearSelected();
        for (MyDrawing d : drawings) {
            Shape reigion = d.getRegion();
            if (reigion != null) {
                if (reigion.intersects(sx, sy, sw, sh)) {
                    selectedDrawings.add(d);
                    d.setSelected(true);
                }
            }
        }
        repaint();
    }
    
}
