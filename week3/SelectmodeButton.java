import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SelectmodeButton extends JButton implements State
{
    StateManager stateManager;
    MyDrawing selectedDrawing;
    int lastX, lastY;

    public SelectmodeButton(StateManager stateManager){
        super("Select");

        addActionListener(new SelectmodeListener());

        this.stateManager = stateManager;
    }

    class SelectmodeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(SelectmodeButton.this);
        }
    }

    @Override
    public void mouseDown(int x, int y){ 
        stateManager.getMediator().setSelected(x, y);
        lastX = x;
        lastY = y;
    }

    public void mouseUp(int x, int y){
        
    }

    public void mouseDrag(int x, int y){
        int dx = x - lastX;
        int dy = y - lastY;
        if (stateManager.getMediator().getSelectedDrawing() != null){
            stateManager.getMediator().move(dx, dy);
            lastX = x;
            lastY = y;

            stateManager.getMediator().repaint();
        }
    }
}