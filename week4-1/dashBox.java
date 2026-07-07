import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class dashBox extends JCheckBox
{
        StateManager stateManager;

    public dashBox(StateManager stateManager){
        super("dash");

        addItemListener(new dashcheckBoxListener());

        this.stateManager = stateManager;
    }

    class dashcheckBoxListener implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED)
            stateManager.setDashed(true);
        else
            stateManager.setDashed(false);
    }
    }
}
