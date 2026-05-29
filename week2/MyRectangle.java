import java.awt.*;

public class MyRectangle extends MyDrawing
{
    public MyRectangle(int xpt, int ypt){
        super();
        setLocation(xpt, ypt);
        //setDashed(true);
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

        if(getDashed()){
            g2.setStroke(new MyDashStroke(getLineWidth()));
        }
        else{
            g2.setStroke(new BasicStroke(getLineWidth()));
        }
        //g2.setStroke(new BasicStroke(getLineWidth()));
        g2.setColor(getFillColor());
        //g2.fillRect(x, y, w, h);
        g2.setColor(getLineColor());
        g2.drawRect(x, y, w, h);
    }

}
