import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class changeDash extends JCheckBox
{
    StateManager stateManager;

    public changeDash(StateManager stateManager){
        super("shortdash");

        addItemListener(new CdashcheckBoxListener());

        this.stateManager = stateManager;
    }

    class CdashcheckBoxListener implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED)
            stateManager.setShortDashed(true);
        else
            stateManager.setShortDashed(false);
    }
    }
}
