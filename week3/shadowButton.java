import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class shadowButton extends JCheckBox
{
    StateManager stateManager;

    public shadowButton(StateManager stateManager){
        super("shadow");

        addItemListener(new shadowcheckBoxListener());

        this.stateManager = stateManager;
    }

    class shadowcheckBoxListener implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED)
            stateManager.setShadow(true);
        else
            stateManager.setShadow(false);
    }
    }
}
