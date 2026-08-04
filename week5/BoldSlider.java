import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoldSlider extends JSlider implements ChangeListener
{
    private Mediator mediator;

    public BoldSlider(Mediator mediator)
    {
        super(1, 20, 1);
        this.mediator = mediator;

        this.setMajorTickSpacing(5);
        this.setMinorTickSpacing(1);
        this.setPaintTicks(true);
        this.setPaintLabels(true);

        this.addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        int boldValue = this.getValue();
        mediator.setLineWidth(boldValue);
        mediator.repaint();
        if (!this.getValueIsAdjusting()) {
            mediator.saveState();
        }
    }
}
