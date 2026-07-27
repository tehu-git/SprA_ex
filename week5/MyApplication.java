import java.awt.*;
import java.util.Vector;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.event.*;

public class MyApplication extends JFrame implements ActionListener
{

    StateManager stateManager;
    MyCanvas canvas;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JSlider boldSlider, alphaSlider;
    private JMenu colorMenu;
    private JMenuItem saveItem, loadItem;
    private JMenuItem redItem, blueItem, greenItem, elseItem;

    private String[] colorOptions = {"Red", "Blue", "Green", "others"};



    public MyApplication(){
        super("My Painter");
        //初期設定
        //setLookAndFeel();
        //キャンバスの作成・初期化
        canvas = new MyCanvas();
        canvas.setBackground(Color.white);
        canvas.setFocusable(true);
        stateManager = new StateManager(canvas);
        //各パネル構築のメソッド化
        setUpMenuBar();
        JPanel toolPanel = createToolPanel();
        JPanel propertyPanel = createPropertyPanel();
        //sJPanel colorPanel = createColorPanel();

        //レイアウトの設定
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(propertyPanel, BorderLayout.NORTH);
        getContentPane().add(toolPanel, BorderLayout.WEST);
        getContentPane().add(canvas, BorderLayout.CENTER);
        //getContentPane().add(colorWrapper, BorderLayout.EAST);
        
        


        //パネルの設定
        //JPanel jp = new JPanel();
        //jp.setLayout(new FlowLayout());

        //削除予定
        //JPanel toolPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        //toolPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 3, 1));
        
        //削除予定
        //JPanel propertyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));


        //カラーパネルの作成
        JColorChooser colorChooser = new JColorChooser();

        //カラーパネルのプレビューを非表示にする
        colorChooser.setPreviewPanel(new JPanel());

        //カラーパネルのタブを1つだけにする
        javax.swing.colorchooser.AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (int i = 1; i < panels.length; i++) {
            colorChooser.removeChooserPanel(panels[i]);
        }

        //カラーパネルがLayout.Eastによって引き伸ばされないようにする
        JPanel colorWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        colorWrapper.add(colorChooser);


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
                    Vector<MyDrawing> selected = med.getSelectedDrawings();
                    if(selected != null){
                        for (MyDrawing d : selected){
                            med.removeDrawing(d);
                        }
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
    //メニューバーの構築
    private void setUpMenuBar(){
        // メニューバーの作成
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        fileMenu = new JMenu("File");
        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        saveItem.addActionListener(new FileSystemlistener(stateManager.getMediator(), 0));
        loadItem.addActionListener(new FileSystemlistener(stateManager.getMediator(), 1));

        menuBar.add(fileMenu);
    }
    
    //見た目変更のメソッド
    private void setLookAndFeel(){
        try {
            // 5:見た目変更
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Font modernFont = new Font("SansSerif", Font.PLAIN, 12);
        
            // アプリケーション内のすべてのUI部品のデフォルトフォントを上書きする
            java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = UIManager.get(key);
                if (value instanceof javax.swing.plaf.FontUIResource) {
                    UIManager.put(key, new javax.swing.plaf.FontUIResource(modernFont));
                }
            }
            } catch (Exception e) {
                e.printStackTrace();
        }
    }

    //ツールパネルの作成メソッド
    private JPanel createToolPanel(){
        JPanel toolPanel = new JPanel (new GridLayout(0, 1, 5, 5));
        toolPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 3, 1));

        //ツールパネルにボタンを追加
        SelectmodeButton selectButton = new SelectmodeButton(stateManager);
        toolPanel.add(selectButton);
        RectButton rectButton = new RectButton(stateManager);
        toolPanel.add(rectButton);
        OvalButton ovalbutton = new OvalButton(stateManager);
        toolPanel.add(ovalbutton);
        fiveStarButton starButton = new fiveStarButton(stateManager);
        toolPanel.add(starButton);
        HendecagonButton hendecagButton = new HendecagonButton(stateManager);
        toolPanel.add(hendecagButton);

        return toolPanel;
    }

    //プロパティパネルの作成メソッド
    private JPanel createPropertyPanel(){
        JPanel propertyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        //fillColorのコンボボックスを作成
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

        //LineColorのコンボボックスを作成
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

        //ラベルの作成
        JLabel colorLabel = new JLabel("Select Color:");
        JLabel lineLabel = new JLabel("Select Line Color:");

        //プロパティパネルにボタンを追加
        shadowButton shadowbutton = new shadowButton(stateManager);
        propertyPanel.add(shadowbutton);
        dashBox dashbox = new dashBox(stateManager);
        propertyPanel.add(dashbox);
        changeDash changeDash = new changeDash(stateManager);
        propertyPanel.add(changeDash);
        boldBox boldbox = new boldBox(stateManager);
        propertyPanel.add(boldbox);
        tripleLineBox triplinebox = new tripleLineBox(stateManager);
        propertyPanel.add(triplinebox);

        propertyPanel.add(lineLabel);
        propertyPanel.add(lineColorComboBox);

        propertyPanel.add(colorLabel);
        propertyPanel.add(colorComboBox);  

        return propertyPanel;
    }

    //右部分のパネルの作成
    private JPanel createRightPanel(){
        JPanel rightPanel = new JPanel();

        return rightPanel;
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