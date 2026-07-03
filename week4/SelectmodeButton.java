import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SelectmodeButton extends JButton implements State
{
    StateManager stateManager;
    MyDrawing selectedDrawing;
    int lastX, lastY;
    int startX, startY;
    MyDrawing selectRect;

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
        if (stateManager.getMediator().getSelectedDrawings() != null){
            lastX = x;
            lastY = y;
        }
        else {
            startX = x;
            startY = y;
            selectRect = new MyRectangle(x, y, 0, 0);
            selectRect.setDashed(true);
        }
    }

    public void mouseUp(int x, int y){
        if (selectRect != null){
            
        }
    }

    public void mouseDrag(int x, int y){
        if (stateManager.getMediator().getSelectedDrawings() != null){
            int dx = x - lastX;
            int dy = y - lastY;
            if (stateManager.getMediator().getSelectedDrawings() != null){
                stateManager.getMediator().move(dx, dy);
                lastX = x;
                lastY = y;

                stateManager.getMediator().repaint();
            }
        }
        else {
            if (selectRect != null){
                int drawX = Math.min(x, startX);
                int drawY = Math.min(y, startY);
                selectRect.setLocation(drawX, drawY);
                selectRect.setW(Math.abs(x - startX));
                selectRect.setH(Math.abs(y - startY));
            }
        }
    }
}