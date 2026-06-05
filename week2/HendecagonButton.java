import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HendecagonButton extends JButton implements State
{
    StateManager stateManager;
    MyDrawing currentDrawing;
    int startX, startY;

    public HendecagonButton(StateManager stateManager){
        super("Hendecagon");

        addActionListener(new HendecagonListener());

        this.stateManager = stateManager;
    }

    class HendecagonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(HendecagonButton.this);
        }
    }

    @Override
    public void mouseDown(int x, int y){
        startX = x;
        startY = y;
        currentDrawing = new MyHendecagonal(x, y, 0, 0);
        stateManager.addDrawing(currentDrawing);
        
        //currentDrawing.setDashed(true);
    }

    public void mouseUp(int x, int y){
        //currentDrawing.setDashed(false);
    }

    
    public void mouseDrag(int x, int y){
        if(currentDrawing != null){
            int size = Math.max(Math.abs(x - startX), Math.abs(y - startY));

            int newX;
            if(x < startX) {
                newX = startX - size;
            } else {
                newX = startX;
            }
            int newY;
            if(y < startY) {
                newY = startY - size;
            } else {
                newY = startY;
            }

            currentDrawing.setLocation(newX, newY);

            currentDrawing.setSize(size);
        }
    }
}
