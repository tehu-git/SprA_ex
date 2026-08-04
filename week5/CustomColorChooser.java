import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;

public class CustomColorChooser extends JColorChooser implements ChangeListener
{
    private Mediator mediator;
    private JRadioButton lineRadio;


    public CustomColorChooser(Mediator mediator, JRadioButton lineRadio) {
        super();
        this.mediator = mediator;
        this.lineRadio = lineRadio;

        this.setPreviewPanel(new JPanel());
        AbstractColorChooserPanel[] panels = this.getChooserPanels();
        for (int i = 1; i < panels.length; i++) {
            this.removeChooserPanel(panels[i]);
        }

        this.getSelectionModel().addChangeListener(this);
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        Color selectedColor = this.getColor();
        if (lineRadio.isSelected()) {
            mediator.setLineColor(selectedColor);
        } else {
            mediator.setColor(selectedColor);
        }
        mediator.repaint();
    }
}
