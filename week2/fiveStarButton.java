import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class fiveStarButton extends JButton implements State
{
    StateManager stateManager;
    MyDrawing currentDrawing;
    MyDrawing supportrect;
    int startX, startY;

    public fiveStarButton(StateManager stateManager){
        super("fivestar");

        addActionListener(new fiveStarListener());

        this.stateManager = stateManager;
    }

    class fiveStarListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(fiveStarButton.this);
        }
    }

    @Override
    public void mouseDown(int x, int y){
        currentDrawing = new MyfiveStar(x, y, 0, 0);
        startX = x;
        startY = y;
        stateManager.addDrawing(currentDrawing);
        //currentDrawing.setDashed(true);
    }
    
    public void mouseUp(int x, int y){
        //currentDrawing.setDashed(false);
    }

    public void mouseDrag(int x, int y){
        if(currentDrawing != null){
            int size = Math.max(Math.abs(x - startX), Math.abs(y - startY));

            int drawx;
            if (x < startX) {
                // マウスが始点より「左」に移動した場合
                // 始点からサイズ分だけ左にズラした位置が左上になる
                drawx = startX - size;
            } else {
                // マウスが始点より「右」に移動した場合
                // 始点がそのまま左上になる
                drawx = startX;
            }

            int drawy;
            if (y < startY) {
                // マウスが始点より「上」に移動した場合
                // 始点からサイズ分だけ上にズラした位置が左上になる
                drawy = startY - size;
            } else {
                // マウスが始点より「下」に移動した場合
                // 始点がそのまま左上になる
                drawy = startY;
            }

            // 3. 計算した正しい座標とサイズをセットする
            currentDrawing.setLocation(drawx, drawy);
            
            // ※ MyDrawingに setSize(w, h) を作っている前提です
            currentDrawing.setSize(size); 
            

        }
    }

}

