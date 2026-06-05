import java.awt.*;

public class MyRectangle extends MyDrawing
{
    public MyRectangle(int xpt, int ypt){
        super();
        setLocation(xpt, ypt);
        //setDashed(true);
    }

    public MyRectangle(int x, int y, int w, int h){
        super(x, y, w, h);
    }

    public MyRectangle(int x, int y, boolean isdashed){
        super(x, y, isdashed);
    }

    public void draw(Graphics g){
        
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();

        Graphics2D g2 = (Graphics2D) g;
        if (getShadow()) {
        g2.setColor(Color.black);
        g2.fillRect(x + 5, y + 5, w, h); 
        }

        if(getDashed()){
            if (getShortDashed()){
                g2.setStroke(new MyshortDashStroke(getLineWidth()));
            }
            else{
                g2.setStroke(new MyDashStroke(getLineWidth()));
            }
        }
        else{
            g2.setStroke(new BasicStroke(getLineWidth()));
        }
        //g2.setStroke(new BasicStroke(getLineWidth()));
        g2.setColor(getFillColor());
        g2.fillRect(x, y, w, h);
        g2.setColor(getLineColor());
        if (getTripLine()) {
            for (int i = 0; i < 3; i++){
                int minus = 3 * i;
                if (x - minus < 0 || y - minus < 0 || w - 2 * minus < 0 || h - 2 * minus < 0) {

                    g2.drawRect(0, 0, 0, 0);

                }
                g2.drawRect(x + minus, y + minus, w - 2 * minus, h - 2 * minus);
            }
        } else {
            g2.drawRect(x, y, w, h);
        }
        g2.drawRect(x, y, w, h);
    }

}
