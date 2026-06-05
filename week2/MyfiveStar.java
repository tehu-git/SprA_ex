import java.awt.*;

public class MyfiveStar extends MyDrawing
{
    public MyfiveStar(int xpt, int ypt){
        super();
        setLocation(xpt, ypt);
    }

    public MyfiveStar(int x, int y, int w, int h){
        super(x, y, w, h);
    }

    public void draw(Graphics g){
        int x = getX();
        int y = getY();
        int r = getW()/2;
        int basex = x + r;
        int basey = y + r;
        int n = 5;
        double fiverad = 2 * Math.PI / n;

        int xlist[] = new int[n];
        int ylist[] = new int[n];

        for (int i = 0; i < 5; i++){
            xlist[i] =  basex + (int) (r * Math.cos(fiverad* 2 * i - Math.PI/2));
            ylist[i] =  basey + (int) (r * Math.sin(fiverad* 2 * i - Math.PI/2));
        }

        Graphics2D g2 = (Graphics2D) g;
        if(getDashed()){
            g2.setStroke(new MyDashStroke(getLineWidth()));
        }
        else{
            g2.setStroke(new BasicStroke(getLineWidth()));
        }
        //g2.setStroke(new BasicStroke(getLineWidth()));
        //g2.setColor(getFillColor());
        g2.setColor(getLineColor());
        g2.drawPolygon(xlist, ylist, n);
    }
}