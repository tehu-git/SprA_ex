import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class boldBox extends JCheckBox
{
    StateManager stateManager;

    public boldBox(StateManager stateManager){
        super("bold");

        addItemListener(new boldcheckBoxListener());

        this.stateManager = stateManager;
    }

    class boldcheckBoxListener implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED)
            stateManager.setBold(true);
        else
            stateManager.setBold(false);
    }
    }
}
