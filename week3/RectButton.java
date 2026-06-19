import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RectButton extends JButton implements State
{
    StateManager stateManager;
    MyDrawing currentDrawing;
    int startX, startY; 

    public RectButton(StateManager stateManager){
        super("Rectangle");

        addActionListener(new RectListener());

        this.stateManager = stateManager;
    }

    class RectListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(RectButton.this);
        }
    }

    @Override
    public void mouseDown(int x, int y){
        startX = x;
        startY = y;
        currentDrawing = new MyRectangle(x, y, 0, 0);
        currentDrawing.setDashed(stateManager.getDashed());
        stateManager.addDrawing(currentDrawing);
        currentDrawing.setShadow(stateManager.getshadow());
        currentDrawing.setShortDashed(stateManager.getShortDashed());
        currentDrawing.setBold(stateManager.getBold());
        currentDrawing.setTripLine(stateManager.getTripLine());
        currentDrawing.setRegion();
    }

    public void mouseUp(int x, int y){
        //currentDrawing.setDashed(false);
    }

    public void mouseDrag(int x, int y){
        
        if(currentDrawing != null){
            int drawx = Math.min(x, startX);
            int drawy = Math.min(y, startY);

            currentDrawing.setLocation(drawx, drawy);
            currentDrawing.setW(Math.abs(x - startX));
            currentDrawing.setH(Math.abs(y - startY));
        }
    } 
}
