import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {
    JButton exitButton;

    public GUI() {
        super("GUI");

        exitButton = new JButton("Press to Exit");

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(new JLabel("Test Button : "));
        getContentPane().add(exitButton);
    }

    public static void main(String [] args) {
        GUI t = new GUI();
        t.pack();
        t.setVisible(true);
    }
}
