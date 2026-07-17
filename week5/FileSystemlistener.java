import java.awt.*;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
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
        JFileChooser jc = new JFileChooser();
        if (mode == 1) {
            int returnVal = jc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jc.getSelectedFile();
                
                try {
                    FileInputStream fin = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fin);

                    //配列の中身が無検査キャスト、との警告文が出るので消しています
                    //ここを消しても動きます
                    @SuppressWarnings("unchecked")
                    //
                    //
                    Vector<MyDrawing> v = (Vector<MyDrawing>)in.readObject();
                    mediator.setDrawings(v);
                    fin.close();
                } catch (Exception ex) {
                }
            }
        }
        else if (mode == 0) {
            int returnVal = jc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File file = jc.getSelectedFile();
                try {
                    FileOutputStream fout = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fout);

                    out.writeObject(mediator.getDrawings());
                    out.flush();

                    fout.close();
                } catch (Exception ex) {
                }
            }
        }

    }
}
