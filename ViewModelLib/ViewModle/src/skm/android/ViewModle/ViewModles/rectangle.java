package skm.android.ViewModle.ViewModles;

import android.graphics.*;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 03/04/13
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public class rectangle {

        Point center;

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
        String month;
        int fraction = 0;



        public rectangle(int colour,Rect bounds, String month){

            this.month = month;
            this.center=center;
            this.bounds=bounds;
            paint= new Paint();
            paint.setColor(colour);
            paint.setAntiAlias(true);
            ballBounds= new RectF(bounds);
            aceleration= new Point(0,2);
            velosity= new Point(0,(int)Math.round(5+Math.random()*18));
            //initialVelosity = velosity;
            latTime=currentTime=System.currentTimeMillis();
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
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

            return bounds.contains(p.x, p.y);
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

            Paint colour = new Paint();
            //colour.setColor(Color.parseColor("#ff00ff"));
            colour.setColor(Color.WHITE);

            RectF colourRec = new RectF(ballBounds.left+2, ballBounds.top+2, ballBounds.right-2, ballBounds.bottom-2);


            c.drawRect(colourRec, this.paint);
//            c.drawRect(this.ballBounds, this.paint);
            c.drawText(getMonth(), ballBounds.left+(ballBounds.width()/2), ballBounds.top+(ballBounds.height()/2), colour);
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

        public Point getCenter() {
            return center;
        }

        public void Update() {




        }

}
