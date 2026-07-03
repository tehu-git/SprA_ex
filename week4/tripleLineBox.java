import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class tripleLineBox extends JCheckBox
{
    StateManager stateManager;

    public tripleLineBox(StateManager stateManager){
        super("Triple Line");

        addItemListener(new tripleLinecheckBoxListener());

        this.stateManager = stateManager;
    }

    class tripleLinecheckBoxListener implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED)
            stateManager.setTripLine(true);
        else
            stateManager.setTripLine(false);
    }
    }
}
