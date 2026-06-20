import java.awt.*;

import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.event.*;

public class MyApplication extends JFrame implements ActionListener
{

    StateManager stateManager;
    MyCanvas canvas;
    private JMenuBar menuBar;
    private JMenu colorMenu;
    private JMenuItem redItem, blueItem, greenItem, elseItem;

    public MyApplication(){
        super("My Painter");

        canvas = new MyCanvas();
        canvas.setBackground(Color.white);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());

        stateManager = new StateManager(canvas);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        colorMenu = new JMenu("Color");
        redItem = new JMenuItem("Red");
        blueItem = new JMenuItem("Blue");
        greenItem = new JMenuItem("Green");
        elseItem = new JMenuItem("else");

        colorMenu.add(redItem);
        colorMenu.add(blueItem);
        colorMenu.add(greenItem);
        colorMenu.add(elseItem);
        redItem.addActionListener(new ChangeColorListener(stateManager.getMediator(), Color.red));
        blueItem.addActionListener(new ChangeColorListener(stateManager.getMediator(), Color.blue));
        greenItem.addActionListener(new ChangeColorListener(stateManager.getMediator(), Color.green));

        menuBar.add(colorMenu);
        
        SelectmodeButton selectButton = new SelectmodeButton(stateManager);
        jp.add(selectButton);
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
        return new Dimension(800, 600);
    }


    public static void main(String[] args){
        MyApplication app = new MyApplication();
        app.setSize(800, 600);
        app.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == redItem){
            stateManager.getMediator().setColor(Color.red);
        }
        else if (e.getSource() == blueItem){
            stateManager.getMediator().setColor(Color.blue);
        }
        else if (e.getSource() == greenItem){
            stateManager.getMediator().setColor(Color.green);
        }
    }
}