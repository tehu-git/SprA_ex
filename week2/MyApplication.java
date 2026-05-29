import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyApplication extends JFrame
{
    public MyApplication(){
        super("My Painter");

        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        this.getContentPane().add(jp);

        MyCanvas canvas = new MyCanvas();
        for (int i = 0; i<8; i++){
            canvas.addDrawing(new MyHendecagonal(40 + i*40, 40+ i*40));
            canvas.addDrawing(new MyRectangle(40 + i*40, 40+ i*40));
        }
        jp.add(BorderLayout.CENTER, canvas);

        this.addWindowListener(
            new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    System.exit(1);
                }
            }
        );
    }


    public static void main(String[] args){
        MyApplication app = new MyApplication();
        app.setSize(400, 300);
        app.setVisible(true);
    }
}