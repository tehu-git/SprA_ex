import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OvalButton extends JButton implements State
{
    StateManager stateManager;
    int startX, startY;
    MyDrawing currentDrawing;
    
    public OvalButton(StateManager stateManager){
        super("Oval");

        addActionListener(new OvalListener());

        this.stateManager = stateManager;
    }

    class OvalListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(OvalButton.this);
        }
    }

    @Override
    public void mouseDown(int x, int y){
        startX = x;
        startY = y;
        currentDrawing = new MyOval(x, y, 0, 0);
        stateManager.addDrawing(currentDrawing);
        currentDrawing.setDashed(stateManager.getDashed());
        currentDrawing.setShadow(stateManager.getshadow());
        currentDrawing.setShortDashed(stateManager.getShortDashed());
        currentDrawing.setBold(stateManager.getBold());
        currentDrawing.setTripLine(stateManager.getTripLine());
    }

    public void mouseUp(int x, int y){
        //currentDrawing.setDashed(false);
    }

    public void mouseDrag(int x, int y){
        if(currentDrawing != null){
            int newX = Math.min(x, startX);
            int newY = Math.min(y, startY);

            currentDrawing.setLocation(newX, newY);

            currentDrawing.setW(Math.abs(x - startX));
            currentDrawing.setH(Math.abs(y - startY));
        }
    }
}
