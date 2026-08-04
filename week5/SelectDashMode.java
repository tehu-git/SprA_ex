import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SelectDashMode extends JPanel implements ActionListener
{
    private StateManager stateManager;

    public SelectDashMode(StateManager stateManager) {
        this.stateManager = stateManager;

        // パネル自身のレイアウトを横並びに設定
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        // ラベルの追加
        this.add(new JLabel("Dash:"));

        // ラジオボタンの作成
        JRadioButton solidButton = new JRadioButton("solid");
        JRadioButton shortDashButton = new JRadioButton("dash1");
        JRadioButton longDashButton = new JRadioButton("dash2");
        JRadioButton dotButton = new JRadioButton("dash3");
        JRadioButton dashDotButton = new JRadioButton("dash4");

        ButtonGroup group = new ButtonGroup();  
        group.add(solidButton);
        group.add(shortDashButton);
        group.add(longDashButton);
        group.add(dotButton);
        group.add(dashDotButton);

        // デフォルトでsolidButtonを選択
        solidButton.setSelected(true);

        // アクションリスナーの追加
        solidButton.addActionListener(this);
        shortDashButton.addActionListener(this);
        longDashButton.addActionListener(this);
        dotButton.addActionListener(this);
        dashDotButton.addActionListener(this);

        this.add(solidButton);
        this.add(shortDashButton);
        this.add(longDashButton);
        this.add(dotButton);
        this.add(dashDotButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // アクションイベントの処理
        String command = e.getActionCommand();
        switch (command) {
            case "solid":
                stateManager.setDashmode(0);
                break;
            case "dash1":
                stateManager.setDashmode(1);
                break;
            case "dash2":
                stateManager.setDashmode(2);
                break;
            case "dash3":
                stateManager.setDashmode(3);
                break;
            case "dash4":
                stateManager.setDashmode(4);
                break;
        }
    }
}
