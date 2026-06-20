import java.awt.*;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.event.*;

public class ChangeColorListener implements ActionListener
{
    private Color mycolor;
    private Mediator mediator;
    
    public ChangeColorListener(Mediator med, Color c){
        this.mediator = med;
        this.mycolor = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.setColor(mycolor);
        mediator.repaint();
    }
}
