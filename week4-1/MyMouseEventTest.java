import javax.swing.*;
import java.awt.event.*;

public class MyMouseEventTest extends JFrame {
    MyMouseEventTest() {
        super("Mouse Event Test");
        addMouseListener(new MyMouseListener());
        setSize(100, 100);

    }

    public static void main(String[] args){
        MyMouseEventTest myapp = new MyMouseEventTest();
        myapp.setVisible(true);
    }
}

class MyMouseListener extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked!");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered!");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Exited!");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed!");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("Released!");
    }
}
