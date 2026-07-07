import java.awt.*;

public class MyshortDashStroke extends BasicStroke
{
    private static float pattern[] = {1, 3};

    public MyshortDashStroke(float linewidth){
        super(linewidth, CAP_BUTT, JOIN_BEVEL, 1.0f, pattern, 0);
    }    
}
