import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyApplication extends JFrame
{

    StateManager stateManager;
    MyCanvas canvas;

    public MyApplication(){
        super("My Painter");

        canvas = new MyCanvas();
        canvas.setBackground(Color.white);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());

        stateManager = new StateManager(canvas);
        
        RectButton rectButton = new RectButton(stateManager);
        jp.add(rectButton);
        OvalButton ovalbutton = new OvalButton(stateManager);
        jp.add(ovalbutton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);

        canvas.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                stateManager.mouseDown(e.getX(), e.getY());
                canvas.repaint();
            }
        });

        this.addWindowListener(
            new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    System.exit(1);
                }
            }
        );
    }

    public Dimension getPreferredSize(){
        return new Dimension(300, 400);
    }


    public static void main(String[] args){
        MyApplication app = new MyApplication();
        app.setSize(400, 300);
        app.setVisible(true);
    }
}