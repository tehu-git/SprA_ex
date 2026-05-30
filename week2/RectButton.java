import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RectButton extends JButton implements State
{
    StateManager stateManager;
    
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
        stateManager.addDrawing(new MyRectangle(x, y));
    }

    public void mouseUp(int x, int y){
        //stateManager.
    }

    public void mouseDrag(int x, int y){
        //stateManager.
    } 
}
