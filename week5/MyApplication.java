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




    public MyApplication(){
        super("My Painter");
        //初期設定
        setLookAndFeel();
        //キャンバスの作成・初期化
        canvas = new MyCanvas();
        canvas.setBackground(Color.white);
        canvas.setFocusable(true);
        stateManager = new StateManager(canvas);
        //各パネル構築のメソッド化
        setUpMenuBar();
        JPanel toolPanel = createToolPanel();
        JTabbedPane rightPanel = createRightPanel();

        //レイアウトの設定
        setupLayout(toolPanel, rightPanel);

        setupEventHandlers();
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

        /* 
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
        */

        return propertyPanel;
    }
        

    //右部分のパネルの作成
    private JTabbedPane createRightPanel(){

        JTabbedPane tabbedPane = new JTabbedPane();

        //1つ目：Colorタブ
        JPanel colorPanel = new JPanel();

        
        
        //切り替え用のラジオボタンを作成
        JRadioButton lineRadio = new JRadioButton("Line Color", true);
        JRadioButton fillRadio = new JRadioButton("Fill Color", false);

        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(lineRadio);
        colorGroup.add(fillRadio);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        radioPanel.add(lineRadio);
        radioPanel.add(fillRadio);

        CustomColorChooser colorChooser = new CustomColorChooser(stateManager.getMediator(), lineRadio);

        JLabel alphaLabel = new JLabel("Alpha:");
        AlphaSlider alphaSlider = new AlphaSlider(stateManager.getMediator(), lineRadio);

        JPanel colorWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        colorWrapper.add(colorChooser);
        

        //JPanel rightPanel = new JPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        colorPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        colorPanel.add(radioPanel);
        colorPanel.add(Box.createVerticalStrut(5)); // ちょっと隙間を開ける
        colorPanel.add(colorWrapper);
        colorPanel.add(Box.createVerticalStrut(15)); 
        colorPanel.add(alphaLabel);
        colorPanel.add(alphaSlider);

        tabbedPane.addTab("Color", colorPanel);

        //2つ目：Propertyタブ
        JPanel propertyPanel = new JPanel();
        propertyPanel.add(new JLabel("Property editor"));

        propertyPanel.setLayout(new BoxLayout(propertyPanel, BoxLayout.Y_AXIS));
        propertyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel boldLabel = new JLabel("Line Width:");
        BoldSlider boldSlider = new BoldSlider(stateManager.getMediator());

        //プロパティパネルにボタンを追加
        shadowButton shadowbutton = new shadowButton(stateManager);
        propertyPanel.add(shadowbutton);
        /*
        dashBox dashbox = new dashBox(stateManager);
        propertyPanel.add(dashbox);
        changeDash changeDash = new changeDash(stateManager);
        propertyPanel.add(changeDash);
         このボタンはスライダーで置き換える
        boldBox boldbox = new boldBox(stateManager);
        propertyPanel.add(boldbox);
        */
        tripleLineBox triplinebox = new tripleLineBox(stateManager);
        propertyPanel.add(triplinebox);

        propertyPanel.add(Box.createVerticalStrut(15));
        propertyPanel.add(new JLabel("Dash Mode:"));
        SelectDashMode selectDashMode = new SelectDashMode(stateManager);
        propertyPanel.add(selectDashMode);

        propertyPanel.add(Box.createVerticalStrut(15));
        propertyPanel.add(boldLabel);
        propertyPanel.add(boldSlider);

        tabbedPane.addTab("Property", propertyPanel);

        return tabbedPane;
    }

    private void setupLayout(JPanel toolPanel, JTabbedPane rightPanel){
        
        getContentPane().setLayout(new BorderLayout());
        //getContentPane().add(propertyPanel, BorderLayout.NORTH);
        getContentPane().add(toolPanel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.EAST);
        getContentPane().add(canvas, BorderLayout.CENTER);
    }
    


    private void setupEventHandlers(){
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
                    med.saveState(); // Save the state after deletion
                }
                else if(e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown()){
                    stateManager.getMediator().undo();
                    System.out.println("Undo");
                }
                else if(e.getKeyCode() == KeyEvent.VK_Y && e.isControlDown()){
                    stateManager.getMediator().redo();
                    System.out.println("Redo");
                }
                else if(e.getKeyCode() == KeyEvent.VK_C && e.isControlDown()){
                    stateManager.getMediator().copy();
                    System.out.println("Copy");
                }
                else if(e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()){
                    stateManager.getMediator().cut();
                    System.out.println("Cut");
                    stateManager.getMediator().saveState(); // Save the state after cut
                }
                else if(e.getKeyCode() == KeyEvent.VK_V && e.isControlDown()){
                    stateManager.getMediator().paste();
                    System.out.println("Paste");
                    stateManager.getMediator().saveState(); // Save the state after paste
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

    //使わなくなる予定
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