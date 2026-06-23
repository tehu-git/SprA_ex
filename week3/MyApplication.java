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

    private String[] colorOptions = {"Red", "Blue", "Green", "others"};



    public MyApplication(){
        super("My Painter");

        canvas = new MyCanvas();
        canvas.setBackground(Color.white);
        canvas.setFocusable(true);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());

        stateManager = new StateManager(canvas);
        /* 
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
        */

        JLabel colorLabel = new JLabel("Select Color:");
        JLabel lineLabel = new JLabel("Select Line Color:");

        JComboBox<String> colorComboBox = new JComboBox<>(colorOptions);
        colorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) colorComboBox.getSelectedItem();
                Mediator mediator = stateManager.getMediator();
                
                if (selected.equals("Red")) {
                    mediator.setColor(Color.red);
                } else if (selected.equals("Blue")) {
                    mediator.setColor(Color.blue);
                } else if (selected.equals("Green")) {
                    mediator.setColor(Color.green);
                } else {
                    Color nowColor = Color.BLACK;
                    Color chosenColor = JColorChooser.showDialog(MyApplication.this, "Choose colors", nowColor);
                    if(chosenColor != null){
                        mediator.setColor(chosenColor);
                    } else {
                        System.out.println("no change/cancelled");
                    }
                }
                mediator.repaint();
            }
        });

        JComboBox<String> lineColorComboBox = new JComboBox<>(colorOptions);
        lineColorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) lineColorComboBox.getSelectedItem();
                Mediator mediator = stateManager.getMediator();
                
                if (selected.equals("Red")) {
                    mediator.setLineColor(Color.red);
                } else if (selected.equals("Blue")) {
                    mediator.setLineColor(Color.blue);
                } else if (selected.equals("Green")) {
                    mediator.setLineColor(Color.green);
                } else {
                    Color nowColor = Color.BLACK;
                    Color chosenColor = JColorChooser.showDialog(MyApplication.this, "Choose colors", nowColor);
                    if(chosenColor != null){
                        mediator.setLineColor(chosenColor);
                    } else {
                        System.out.println("no change/cancelled");
                    }
                }
                mediator.repaint();
            }
        });

        jp.add(lineLabel);
        jp.add(lineColorComboBox);


        jp.add(colorLabel);
        jp.add(colorComboBox);        


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
                canvas.requestFocusInWindow();
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

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_DELETE){
                    Mediator med = stateManager.getMediator();
                    MyDrawing selected = med.getSelectedDrawing();
                    if(selected != null){
                        med.removeDrawing(selected);
                        med.clearSelected();
                        med.repaint();
                    }
                }
                else if(e.getKeyCode() == KeyEvent.VK_C && e.isControlDown()){
                    stateManager.getMediator().copy();
                    System.out.println("Copy");
                }
                else if(e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()){
                    stateManager.getMediator().cut();
                    System.out.println("Cut");
                }
                else if(e.getKeyCode() == KeyEvent.VK_V && e.isControlDown()){
                    //ペーストはマウスの位置に行う
                    stateManager.getMediator().paste();
                    System.out.println("Paste");
                }
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