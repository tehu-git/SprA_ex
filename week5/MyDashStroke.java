import java.awt.*;
public class MyDashStroke extends BasicStroke
{
    private static float pattern1[] = {10, 10};
    private static float pattern2[] = {1, 3};
    private static float pattern3[] = {15, 5, 3, 5};
    private static float pattern4[] = {15, 5, 3, 5, 3, 5};

    public static Stroke passStroke(float linewidth, int dashmode){
        float[] pattern = null;
        switch (dashmode) {
            case 1:
                pattern = pattern1;
                break;
            case 2:
                pattern = pattern2;
                break;
            case 3:
                pattern = pattern3;
                break;
            case 4:
                pattern = pattern4;
                break;
    
            default:
                return new BasicStroke(linewidth);
        }
        return new BasicStroke(linewidth, CAP_BUTT, JOIN_BEVEL, 1.0f, pattern, 0);
    }
}
