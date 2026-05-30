import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class fiveStarButton extends JButton implements State
{
    StateManager stateManager;

    public fiveStarButton(StateManager stateManager){
        super("fivestar");

        addActionListener(new fiveStarListener());

        this.stateManager = stateManager;
    }

    class fiveStarListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(fiveStarButton.this);
        }
    }

    @Override
    public void mouseDown(int x, int y){
        stateManager.addDrawing(new MyfiveStar(x, y));
    }
    
    public void mouseUp(int x, int y){
        //stateManager.
    }

    public void mouseDrag(int x, int y){
        //stateManager.
    }

}

