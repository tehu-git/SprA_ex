import java.awt.*;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AlphaSlider extends JSlider implements ChangeListener
{
    private Mediator mediator;
    private JRadioButton lineRadio;
    private boolean isLine;


    public AlphaSlider(Mediator med, JRadioButton lineRadio) {
        super(0, 255, 255); // 0から255までの範囲で初期値は255
        this.mediator = med;
        this.lineRadio = lineRadio;
        this.setPaintTicks(true);
        this.setPaintLabels(true);

        this.addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int alphaValue = this.getValue();
        boolean isLine = lineRadio.isSelected();
        if (isLine) {
            mediator.setlineAlpha(alphaValue);
        } else {
            mediator.setfillAlpha(alphaValue);
        }
        mediator.repaint();
    }
}
