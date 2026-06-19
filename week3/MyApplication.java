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
        fiveStarButton starButton = new fiveStarButton(stateManager);
        jp.add(starButton);
        HendecagonButton hendecagButton = new HendecagonButton(stateManager);
        jp.add(hendecagButton);
        shadowButton shadowbutton = new shadowButton(stateManager);
        jp.add(shadowbutton);
        dashBox dashbox = new dashBox(stateManager);
        jp.add(dashbox);
        changeDash changeDash = new changeDash(stateManager);
        jp.add(changeDash);
        boldBox boldbox = new boldBox(stateManager);
        jp.add(boldbox);
        tripleLineBox triplinebox = new tripleLineBox(stateManager);
        jp.add(triplinebox);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);

        canvas.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                stateManager.mouseDown(e.getX(), e.getY());
                canvas.repaint();
            }

            public void mouseReleased(MouseEvent e){
                stateManager.mouseUp(e.getX(), e.getY());
                canvas.repaint();
            }
        });

        canvas.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e){
                stateManager.mouseDrag(e.getX(), e.getY());
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