import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SelectmodeButton extends JButton implements State
{
    StateManager stateManager;
    MyDrawing selectedDrawing;

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
        
        selectedDrawing = StateManager.setSelectedDrawing(Mediator.getSelected(x, y));
        if (selectedDrawing != null){
            selectedDrawing.setSelected(true);
        }
        else{
            StateManager.setSelectedDrawing(null);
        }
    }

    public void mouseUp(int x, int y){

    }

    public void mouseDrag(int x, int y){
        
    }
}