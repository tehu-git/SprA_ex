import java.awt.*;

public class MyHendecagonal extends MyDrawing
{
    public MyHendecagonal(int xpt, int ypt){
        super();
        setLocation(xpt, ypt);
    }

    /*public MyHendecagonal(int xpt, int ypt){
        super(xpt, ypt);
        
    }*/

    public MyHendecagonal(int xpt, int ypt, int w, int h){
        super(xpt, ypt, w, h);
    }

    public MyHendecagonal(int xpt, int ypt, int w, int h, Color linecolor){
        super(xpt, ypt, w, h, linecolor);
    }
    

    public void draw(Graphics g){
        int x = getX();
        int y = getY();
        int r = getW()/2;
        int n = 11;
        int basex = x + r;
        int basey = y + r;
        double elevenrad = 2 * Math.PI / n;

        int xlist[] = new int[n];
        int ylist[] = new int[n];
        for (int i = 0; i < 11; i++){
            xlist[i] = basex + (int) (r * Math.cos(i * elevenrad));
            ylist[i] = basey + (int) (r * Math.sin(i * elevenrad));
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(getLineWidth()));
        g2.setColor(getFillColor());
        //g2.fillPolygon(xlist, ylist, n);
        g2.setColor(getLineColor());
        g2.drawPolygon(xlist, ylist, n);
    }
}