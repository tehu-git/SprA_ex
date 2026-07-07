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
        if (!stateManager.getMediator().getSelectedDrawings().isEmpty()){
            lastX = x;
            lastY = y;
        }
        else {
            startX = x;
            startY = y;
            selectRect = new MyRectangle(x, y, 0, 0);
            selectRect.setDashed(true);
            selectRect.setFillColor(new Color(0, 0, 0, 0));
            stateManager.addDrawing(selectRect);
        }
    }

    public void mouseUp(int x, int y){
        if (selectRect != null){
            int rectX = selectRect.getX();
            int rectY = selectRect.getY();
            int rectW = selectRect.getW();
            int rectH = selectRect.getH();
            stateManager.getMediator().removeDrawing(selectRect);
            stateManager.getMediator().RectsetSelected(rectX, rectY, rectW, rectH);
            
        }
        repaint();
    }

    public void mouseDrag(int x, int y){
        if (!stateManager.getMediator().getSelectedDrawings().isEmpty()){
            int dx = x - lastX;
            int dy = y - lastY;
            if (!stateManager.getMediator().getSelectedDrawings().isEmpty()){
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