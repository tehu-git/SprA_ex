import java.awt.*;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class FileSystemlistener implements ActionListener 
{
    private Mediator mediator;
    private Vector<MyDrawing> drawings;
    private int mode; // 0: save, 1: load
    
    public FileSystemlistener(Mediator med, int mode){
        this.mediator = med;
        this.mode = mode;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (mode == 1) {
            try {
                FileInputStream fin = new FileInputStream("file.txt");
                ObjectInputStream in = new ObjectInputStream(fin);

                Vector<MyDrawing> v = (Vector<MyDrawing>)in.readObject();
                mediator.setDrawings(v);
                fin.close();
            } catch (Exception ex) {
            }
        }
        else if (mode == 0) {
            try {
                FileOutputStream fout = new FileOutputStream("file.txt");
                ObjectOutputStream out = new ObjectOutputStream(fout);

                out.writeObject(mediator.getDrawings());
                out.flush();

                fout.close();
            } catch (Exception ex) {
            }
        }

    }
}
