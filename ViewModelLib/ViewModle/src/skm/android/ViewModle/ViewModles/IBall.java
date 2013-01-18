package skm.android.ViewModle.ViewModles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 28/09/12
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public interface IBall{
    void Draw(Canvas c);
    void Update();
    int getRadius();
    Point getCenter();
    void setPaint(Paint p);
    boolean contains(Point p);
    boolean isPressed();
    void setPressed(boolean pressed);
    void setVelosity(Point p);
    void setInitialVelosity(Point p);
}