package skm.android.ViewModle.ViewModles;

import android.graphics.*;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 28/09/12
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */


public class Ball implements IBall{

    Point center;
    int radius;
    Paint paint;
    Rect bounds;
    RectF ballBounds;
    Point velosity;
    Point initialVelosity;
    Point aceleration;
    boolean reverse=false;
    long latTime,currentTime;
    int count=0;
    int minSpeed = -2;
    boolean pressed = false;

    int fraction = 0;



    public Ball(Point center,int colour,int radius,Rect bounds){
        this.center=center;
        this.radius=radius;
        this.bounds=bounds;
        paint= new Paint();
        paint.setColor(colour);
        paint.setAntiAlias(true);
        ballBounds= new RectF(center.x-radius,center.y+radius,center.x+radius,center.y-radius);
        aceleration= new Point(0,2);
        velosity= new Point(0,(int)Math.round(5+Math.random()*18));
        //initialVelosity = velosity;
        latTime=currentTime=System.currentTimeMillis();
    }

    public Point getInitialVelosity() {
        return initialVelosity;
    }

    public void setInitialVelosity(Point initialVelosity) {
        this.initialVelosity = initialVelosity;
        fraction = (int)initialVelosity.y/10;

    }
    public int getCount(){return count;}

    public boolean contains(Point p)
    {
        int deltaX = Math.abs(this.getCenter().x - p.x);
        int deltaY = Math.abs(this.getCenter().y - p.y);
        return (deltaX < this.getRadius() && deltaY < this.getRadius());
    }
    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public Point getVelosity() {
        return velosity;
    }

    public void setVelosity(Point velosity) {
        this.velosity = velosity;
    }

    public void Draw(Canvas c) {
        c.drawOval(this.ballBounds,this.paint);
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
        changeColour();

    }

    public void changeColour()
    {
        if(this.isPressed() == true)
        {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ff00ff"));
            this.setPaint(paint);
        } else {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#00ff00"));
            this.setPaint(paint);
        }
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getRadius() {
        return radius;
    }

    public Point getCenter() {
        return center;
    }

    public void Update() {
//        currentTime=System.currentTimeMillis();
//        double time = 30*(currentTime-latTime)/1000.0;
//        //double y = max(aceleration.y * time, 1);
//        double x = max(aceleration.x * time, 0);


        int value = bounds.height()/2;



        // if the ball is traveling up the screen and has reached 3/4 the height of the screen
        int thing = bounds.height()*1/3;
        int otherThing = center.y;
        if(velosity.y < 0 && center.y < bounds.height()*1/3)
        {
            // slow the ball down as long as it's speed is greater than 1
            if(velosity.y < 1)
            {
                velosity.y+= fraction;
            }
            if(velosity.y == minSpeed)
            {
                velosity.x=-velosity.x;
                velosity.y=-velosity.y;
                reverse=!reverse;
            }
        }
        // speed the ball up because it is falling
        //if(velosity.y > 0 && center.y > bounds.height()*1/3)
        if(velosity.y > 0)
        {
            if(velosity.y < initialVelosity.y)
            {
                //velosity.y++;
                velosity.y += fraction;
            }  else if(velosity.y>initialVelosity.y){
                velosity.y = initialVelosity.y;
            }
        }

        // ball.top = the bottom of the ball, top because it has a higher y value then bottom, and vica versa
        // if the ball is touching or gone past the bottom or top of the screen
        if(bounds.bottom<ballBounds.top||bounds.top>ballBounds.bottom)
        {
            // if the ball has gone pas the bottom of the screen
            if(bounds.bottom<=ballBounds.top)
            {
                count++ ;
            }

            // if the ball is touching or gone past the top bound of the screen
            if(center.y-radius<=bounds.top)
            {
                center.y+=velosity.y;
                float delta =   Math.abs(bounds.top - (center.y-radius));   // get the distance between the center of the ball and the bound,
                center.y=Math.round(bounds.top+radius+delta);   // place the ball on the line and  add the lost distance to the velocity, as if it is not, then velocity is lost
                //center.y = bounds.top+radius;
            }
            else if(center.y+radius>=bounds.bottom)
            {
                center.y+=velosity.y;
                float delta =   Math.abs(center.y+radius- bounds.bottom);
                center.y=Math.round(bounds.bottom-radius -delta);
                //center.y = bounds.bottom-radius;
            }

            velosity.x=-velosity.x;
            velosity.y=-velosity.y;
            reverse=!reverse;



        }else{
            // if the ball is about to go past the bottom of the screen in it's next move (i.e. when velosity is applied to it)
            if(ballBounds.top + velosity.y >= bounds.bottom)
            {
                // find out what the distance is between the ball and the line
                float delta = Math.abs(bounds.bottom - (ballBounds.top + velosity.y));

                // take that value away from the velocity variable,
                // and what ever remains, should be the distance the ball now is from the bottom of the screen
                center.y = bounds.bottom;
                center.y = center.y - (radius + (int)delta);

                // now reverse the direction the ball is moving in
                count++;
                velosity.x=-velosity.x;
                velosity.y=-velosity.y;
                reverse=!reverse;
            }
            else if(ballBounds.bottom + velosity.y <= bounds.top)
            {
                // find out what the distance is between the ball and the line
                float delta = Math.abs(bounds.top - (ballBounds.bottom + velosity.y));

                // take that value away from the velocity variable,
                // and what ever remains, should be the distance the ball now is from the top of the screen
                center.y = bounds.top;
                center.y = center.y + (radius + (int)delta);

                // now reverse the direction the ball is moving in
                velosity.x=-velosity.x;
                velosity.y=-velosity.y;
                reverse=!reverse;
            }
            else
            {

                center.x+=velosity.x;
                center.y+=velosity.y;

            }

        }
        ballBounds= new RectF(center.x-radius,center.y+radius,center.x+radius,center.y-radius);
        latTime=currentTime;


    }
}