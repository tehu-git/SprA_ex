import java.awt.*;

public class MyHendecagonal extends MyDrawing
{
    public MyHendecagonal(int xpt, int ypt){
        super();
        setLocation(xpt, ypt);
    }

    public void draw(Graphics g){
        int x = getX();
        int y = getY();
        int r = getW()/2;
        int n = 11;
        double elevenrad = 2 * Math.PI / n;

        int xlist[] = new int[n];
        int ylist[] = new int[n];
        for (int i = 0; i < 11; i++){
            xlist[i] = x + (int) (r * Math.cos(i * elevenrad));
            ylist[i] = y + (int) (r * Math.sin(i * elevenrad));
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(getLineWidth()));
        g2.setColor(getFillColor());
        //g2.fillPolygon(xlist, ylist, n);
        g2.setColor(getLineColor());
        g2.drawPolygon(xlist, ylist, n);
    }
}