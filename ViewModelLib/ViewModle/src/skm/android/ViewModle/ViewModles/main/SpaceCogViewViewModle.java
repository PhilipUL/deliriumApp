package skm.android.ViewModle.ViewModles.main;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import skm.android.R;
import skm.android.ViewModle.ViewModles.Ball;
import skm.android.ViewModle.ViewModles.IBall;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.base.ViewModleBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 27/09/12
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class SpaceCogViewViewModle extends ViewModleBase implements Serializable {



    List<IClip> clips = new Vector<IClip>();
    View view;
    int ballCountSequence;
    Rect windowBounds;
    Vector<IBall> balls;

    int sequenceNumber = 3;


    List<IBall> demoSequence;
    List<IBall> userSequence;

    public SpaceCogViewViewModle(Context context, View v) {

        super(context);
        ballCountSequence = 2;
        instance = this;
        this.view = v;
        init();
    }

    interface IClip {
        boolean play(Canvas c, Context context);
    }
    interface ITouchClip extends IClip{
        void onTouch(MotionEvent e);
    }
    interface ITestClip extends ITouchClip{
    boolean getResult();
    }

    public void setBallVector(Vector<IBall> b)
    {
        balls = b;
    }

    public Vector<IBall> getBallVector()
    {
        return balls;
    }

    public List<IBall> getDemoSequence() {
        return demoSequence;
    }

    public void setDemoSequence(List<IBall> demoSequence) {
        this.demoSequence = demoSequence;
    }

    public List<IBall> getUserSequence() {
        return userSequence;
    }

    public void setUserSequence(List<IBall> userSequence) {
        this.userSequence = userSequence;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public List<IClip> getClips() {
        return clips;
    }

    public void setClips(List<IClip> clips) {
        this.clips = clips;
    }

    private int count = 0;

    public void onDraw(Canvas c){
        // if we haven't reached the end of the clips, AND, the one we're currently on is finished(returns true)
        // I think this is also where play is executed for the current clip, the update if you will
        if( count < clips.size() && clips.get(count).play(c,getApplicationContext()) )
        {
            if(clips.get(count) instanceof ITestClip)
            {
                ITestClip tClip = (ITestClip) clips.get(count);
                if(tClip.getResult() == true)
                {
                    addClip(clips, new Intermediate(),1);
                    addClip(clips, new Demo(), 1);
                    addClip(clips,new Intermediate(),1);
                    addClip(clips,new Test(),1);
                    setSequenceNumber(getSequenceNumber()+1);

                }
            }
            // then go to the next clip(increment the count)
            count++;
        }
    }

    public boolean onTouchEvent(MotionEvent e){
        // if we haven't reached the end of the clips, AND, the clip we're on has a touch event
        if(count<clips.size() && clips.get(count) instanceof ITouchClip)
        {
            // then execute that touch event and pass the MotionEvent context object to it.
            ((ITouchClip) clips.get(count)).onTouch(e);
        }
        return true;
    }




    private static SpaceCogViewViewModle instance = null;



    public static SpaceCogViewViewModle getInstance(Context c,View v){
        return instance!=null?instance:(instance=new SpaceCogViewViewModle(c,v));
    }

    public void clear(){
        instance=null;
    }

    public void init()
    {


        //clips  = getClips();
        addClip(clips,start,1);
        addClip(clips,new Demo(),1);
        addClip(clips,new Intermediate(),1);
        //addClip(clips,start,1);
        addClip(clips,new Test(),1);

        //addClip(clips,end,1);
        // come up with a random sequence of months
    }

    private void addClip(List<IClip> clips,IClip clip,int times){
        for(int i=0;i<times;i++) clips.add(clip);
    }

    public class Intermediate implements ITouchClip
    {
        boolean finished = false;
        Paint paint = null;

        boolean started =false;
        Rect bounds ;

        public boolean play(Canvas out,Context context)
        {
            float aspect = out.getHeight()*1.0f/out.getWidth();
            int width = 1000;
            Bitmap bitmap = Bitmap.createBitmap(width,Math.round(width*aspect), Bitmap.Config.RGB_565);
            Canvas c = new Canvas(bitmap);
            bounds = c.getClipBounds();
            windowBounds = out.getClipBounds();

            if(paint==null){
                String currentSize = Shared.getOptionAtribute(context.getString(R.string.FontSize), getString(R.string.current), context);
                String textColour = Shared.getOptionAtribute(context.getString(R.string.Colours), getString(R.string.text), context);
                paint=new Paint();
                paint.setColor(Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" + textColour, context).getTextContent()));
                paint.setTextSize(new Integer(Shared.getOption(context.getString(R.string.FontSize) +"/"+ currentSize,context).getTextContent()));
                String font = Shared.getOptionAtribute(getString(R.string.Font), getString(R.string.current), getApplicationContext());
                Typeface face=Typeface.createFromAsset(getAssets(), Shared.getOption(getString(R.string.Font)+"/"+font,getApplicationContext()).getTextContent());
                paint.setTypeface(face);

            }
            c.drawText("tap the screen",50,50,paint);
            c.drawText("to start",50,100,paint);
            c.drawText("the test.",50,150,paint);
            c.drawText("count how many",50,200,paint);
            c.drawText("times the pink",50,250,paint);
            c.drawText("ball WHAAAAAAAAAAAAAA",50,300,paint);

            out.drawBitmap(bitmap,c.getClipBounds(),out.getClipBounds(),paint);

            return finished;
        }
        public void onTouch(MotionEvent e) {

            finished=true;
        }
    }


    private IClip start = new ITouchClip(){
        boolean finished = false;
        Paint paint = null;

        boolean started =false;
        Rect bounds ;


        public boolean play(Canvas out,Context context){

            float aspect = out.getHeight()*1.0f/out.getWidth();
            int width = 1000;
            Bitmap bitmap = Bitmap.createBitmap(width,Math.round(width*aspect), Bitmap.Config.RGB_565);
            Canvas c = new Canvas(bitmap);
            bounds = c.getClipBounds();
            windowBounds = out.getClipBounds();

            if(!started){
                balls=new Vector<IBall>();
                //months.add(new Ball(new Point(bounds.width()/4,10+(2+random.nextInt(10))*(1+random.nextInt(10))),Color.parseColor("#00ff00"),45,bounds));
                balls.add(new Ball(new Point(bounds.width()/4, bounds.height()/2), Color.parseColor("#00ff00"), 45, bounds));
                balls.add(new Ball(new Point((bounds.width()/4)*2, bounds.height()/2), Color.parseColor("#00ff00"), 45, bounds));
                balls.add(new Ball(new Point((bounds.width()/4)*3, bounds.height()/2), Color.parseColor("#00ff00"), 45, bounds));

                setBallVector(balls);
                started = true;
            }

            if(paint==null){
                String currentSize = Shared.getOptionAtribute(context.getString(R.string.FontSize), getString(R.string.current), context);
                String textColour = Shared.getOptionAtribute(context.getString(R.string.Colours), getString(R.string.text), context);
                paint=new Paint();
                paint.setColor(Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" + textColour, context).getTextContent()));
                paint.setTextSize(new Integer(Shared.getOption(context.getString(R.string.FontSize) +"/"+ currentSize,context).getTextContent()));
                String font = Shared.getOptionAtribute(getString(R.string.Font), getString(R.string.current), getApplicationContext());
                Typeface face=Typeface.createFromAsset(getAssets(), Shared.getOption(getString(R.string.Font)+"/"+font,getApplicationContext()).getTextContent());
                paint.setTypeface(face);

            }
            c.drawText("tap the screen",50,50,paint);
            c.drawText("to start",50,100,paint);
            c.drawText("the test.",50,150,paint);
            c.drawText("count how many",50,200,paint);
            c.drawText("times the pink",50,250,paint);
            c.drawText("ball WHAAAAAAAAAAAAAA",50,300,paint);

            out.drawBitmap(bitmap,c.getClipBounds(),out.getClipBounds(),paint);

            return finished;
        }

        public void onTouch(MotionEvent e) {

            finished=true;
        }
    };

    private class Demo implements IClip
    {
        // need to create a process which increments the number of highlighted months
        // which will require a class variable in SpaceCogViewViewModle
        Vector<IBall> balls = getBallVector();
        List<IBall> sequence = new ArrayList<IBall>();
        boolean started =false;
        Random random = new Random();
        Rect bounds ;
        int count = 0;
        long startTime = 0;
        long endTime = 0;
        int sequenceNumber= 4;

        public boolean play(Canvas out,Context context)
        {
            balls = getBallVector();
            Paint paint = null;
            float aspect = out.getHeight()*1.0f/out.getWidth();
            int width = 1000;
            Bitmap bitmap = Bitmap.createBitmap(width,Math.round(width*aspect), Bitmap.Config.RGB_565);
            Canvas c = new Canvas(bitmap);
            bounds = c.getClipBounds();
            windowBounds = out.getClipBounds();


            // here we need to create a random list of references to the months to be compared to the list created by the user
            if(!started)
            {
                sequenceNumber = getSequenceNumber();
                // create a list of random months from the months Vector. SequenceNumber dictates how many random months will be stored in the sequence variable
                for(int i = 0; i< sequenceNumber; i++)
                {
                    int randomInt = random.nextInt(balls.size());
                    sequence.add(balls.get(randomInt));
                }

                // NB
                // set the SpaceCogViewViewModle's sequence variable
                setDemoSequence(sequence);


                startTime = System.currentTimeMillis();
                endTime = System.currentTimeMillis() + (2000*(sequence.size()));

                started = true;
            }


            // go to the first ball, and display it as a different colour for 2 seconds
            long current = System.currentTimeMillis();
            if(current < (startTime + (2000*(count+1))))
            {
                sequence.get(count).setPressed(true);
            }else{
                // then set it back to not pressed and progress to the next ball in the sequence by incrementing the counter.
                sequence.get(count).setPressed(false);
                count++;
            }

            // draw all the months
            for(IBall ball: balls)
            {
                ball.Draw(c);
            }

            if(paint==null){
                String currentSize = Shared.getOptionAtribute(context.getString(R.string.FontSize), getString(R.string.current), context);
                String textColour = Shared.getOptionAtribute(context.getString(R.string.Colours), getString(R.string.text), context);
                paint=new Paint();
                paint.setColor(Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" + textColour, context).getTextContent()));
                paint.setTextSize(new Integer(Shared.getOption(context.getString(R.string.FontSize) +"/"+ currentSize,context).getTextContent()));
                String font = Shared.getOptionAtribute(getString(R.string.Font), getString(R.string.current), getApplicationContext());
                Typeface face=Typeface.createFromAsset(getAssets(), Shared.getOption(getString(R.string.Font)+"/"+font,getApplicationContext()).getTextContent());
                paint.setTypeface(face);

            }

            out.drawBitmap(bitmap,c.getClipBounds(),out.getClipBounds(),paint);


            //if(System.currentTimeMillis() > endTime)
            // if we are on the last ball of the sequence, AND that ball is now currently set back to false
            if(count == sequence.size() && sequence.get(count-1).isPressed() == false)
            {
                return true;
            }else{
                return false;
            }
        }
    }
//    private IClip demo = new IClip() {
//        // need to create a process which increments the number of highlighted months
//        // which will require a class variable in SpaceCogViewViewModle
//        Vector<IBall> months = getBallVector();
//        List<IBall> sequence = new ArrayList<IBall>();
//        boolean started =false;
//        Random random = new Random();
//        Rect bounds ;
//        int count = 0;
//        long startTime = 0;
//        long endTime = 0;
//        int sequenceNumber= 4;
//
//        public boolean play(Canvas out,Context context) {
//            months = getBallVector();
//            Paint paint = null;
//            float aspect = out.getHeight()*1.0f/out.getWidth();
//            int width = 1000;
//            Bitmap bitmap = Bitmap.createBitmap(width,Math.round(width*aspect), Bitmap.Config.RGB_565);
//            Canvas c = new Canvas(bitmap);
//            bounds = c.getClipBounds();
//            windowBounds = out.getClipBounds();
//
//
//            // here we need to create a random list of references to the months to be compared to the list created by the user
//            if(!started)
//            {
//                sequenceNumber = getSequenceNumber();
//                // create a list of random months from the months Vector. SequenceNumber dictates how many random months will be stored in the sequence variable
//                for(int i = 0; i< sequenceNumber; i++)
//                {
//                    int randomInt = random.nextInt(months.size());
//                    sequence.add(months.get(randomInt));
//                }
//
//                // NB
//                // set the SpaceCogViewViewModle's sequence variable
//                setDemoSequence(sequence);
//
//
//                startTime = System.currentTimeMillis();
//                endTime = System.currentTimeMillis() + (2000*(sequence.size()));
//
//                started = true;
//            }
//
//
//            // go to the first ball, and display it as a different colour for 2 seconds
//            long current = System.currentTimeMillis();
//            if(current < (startTime + (2000*(count+1))))
//            {
//                sequence.get(count).setPressed(true);
//            }else{
//                // then set it back to not pressed and progress to the next ball in the sequence by incrementing the counter.
//                sequence.get(count).setPressed(false);
//                count++;
//            }
//
//            // draw all the months
//            for(IBall ball: months)
//            {
//                ball.Draw(c);
//            }
//
//            if(paint==null){
//                String currentSize = Shared.getOptionAtribute(context.getString(R.string.FontSize), getString(R.string.current), context);
//                String textColour = Shared.getOptionAtribute(context.getString(R.string.Colours), getString(R.string.text), context);
//                paint=new Paint();
//                paint.setColor(Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" + textColour, context).getTextContent()));
//                paint.setTextSize(new Integer(Shared.getOption(context.getString(R.string.FontSize) +"/"+ currentSize,context).getTextContent()));
//                String font = Shared.getOptionAtribute(getString(R.string.Font), getString(R.string.current), getApplicationContext());
//                Typeface face=Typeface.createFromAsset(getAssets(), Shared.getOption(getString(R.string.Font)+"/"+font,getApplicationContext()).getTextContent());
//                paint.setTypeface(face);
//
//            }
//
//            out.drawBitmap(bitmap,c.getClipBounds(),out.getClipBounds(),paint);
//
//
//            //if(System.currentTimeMillis() > endTime)
//            // if we are on the last ball of the sequence, AND that ball is now currently set back to false
//            if(count == sequence.size() && sequence.get(count-1).isPressed() == false)
//            {
//                return true;
//            }else{
//                return false;
//            }
//        }
//    };

    private class Test implements ITestClip
    {
        int ballPressCount = 0;
        boolean started =false;
        Vector<IBall> balls;
        Rect bounds ;
        boolean sequencesMatch = true;
        List<IBall> userSequenceTest = new ArrayList<IBall>();
        Boolean successfulPlay= false;

        public boolean getResult()
        {
            return successfulPlay;
        }

        public void setResult(Boolean b)
        {
            successfulPlay = b;
        }

        public boolean play(Canvas out,Context context)
        {
            Paint paint = null;
            float aspect = out.getHeight()*1.0f/out.getWidth();
            int width = 1000;
            Bitmap bitmap = Bitmap.createBitmap(width,Math.round(width*aspect), Bitmap.Config.RGB_565);
            Canvas c = new Canvas(bitmap);
            bounds = c.getClipBounds();
            windowBounds = out.getClipBounds();

            if(!started){
                started = true;
                balls= getBallVector();

                paint= new Paint();
                paint.setColor(Color.parseColor("#00ff00"));
                paint.setStrokeWidth(5);
            }

            for(IBall ball :balls){
                //ball.Update();  // the months don't need to be updated
                ball.Draw(c);
            }

            out.drawBitmap(bitmap,c.getClipBounds(),out.getClipBounds(),paint);


            for(int i = 0; i < userSequenceTest.size(); i++)
            {
                if(demoSequence.get(i) != userSequenceTest.get(i))
                {
                    sequencesMatch = false;
                }

                if(sequencesMatch == false)
                {
                    break;
                }

            }



            // if we've pressed more circles than are in the sequence, then we have failed.
            if(ballPressCount > getDemoSequence().size() || sequencesMatch == false)
            {
                // return true when finished
                for(IBall ball: balls)
                {
                    ball.setPressed(false);
                }
                setResult(false);
                return true;
            }
            // if the sequences match and we have pressed the number of months in the demo sequence
            else if(sequencesMatch == true && ballPressCount == demoSequence.size()){
                // then we have succeded and return true because we have finished

                setResult(true);
                return true;
            } else {
                return false;
            }



        }


        public void onTouch(MotionEvent e){
            switch(e.getAction()){
                case MotionEvent.ACTION_DOWN:
                    // figure out if a ball has been touched
                    //
                    Point touchCoord = new Point((int) (e.getX()*(bounds.width()*1.0/windowBounds.width())),(int) (e.getY()*(bounds.height()*1.0/windowBounds.height())));

                    for(IBall ball :balls)
                    {
                        // if the places that we touched happens to be in the area of the ball, then set ball touch to true
                        if(ball.contains(touchCoord))
                        {
                            ball.setPressed(true);
//                            // add ball pressed to userSequenceTest, so that we can compare with demoSequence
//                            userSequenceTest.add(ball);
//
//                            monthPressedCount++;
                        }
                        else // if we didn't touch the ball
                        {
                            // then set all the months to false
                            ball.setPressed(false);
                        }

                    }
                    break;

                case MotionEvent.ACTION_UP:

                    for(IBall ball: balls)
                    {
                        if(ball.isPressed() == true)
                        {
                            // add ball pressed to userSequenceTest, so that we can compare with demoSequence
                            userSequenceTest.add(ball);

                            ballPressCount++;
                            ball.setPressed(false);
                        }
                    }
                    break;
            }
            // if it has, add it to the users sequence
            //if(count<clips.size()&&clips.get(count) instanceof ITouchClip)((ITouchClip) clips.get(count)).onTouch(e);
            //return true;
        }
    }
//    private IClip test = new ITestClip()
//    {
//        int monthPressedCount = 0;
//        boolean started =false;
//        Vector<IBall> months;
//        Rect bounds ;
//        boolean sequencesMatch = true;
//        List<IBall> userSequenceTest = new ArrayList<IBall>();
//        Boolean successfulPlay= false;
//
//        public boolean getResult()
//        {
//            return successfulPlay;
//        }
//
//        public void setResult(Boolean b)
//        {
//            successfulPlay = b;
//        }
//
//        public boolean play(Canvas out,Context context)
//        {
//            Paint paint = null;
//            float aspect = out.getHeight()*1.0f/out.getWidth();
//            int width = 1000;
//            Bitmap bitmap = Bitmap.createBitmap(width,Math.round(width*aspect), Bitmap.Config.RGB_565);
//            Canvas c = new Canvas(bitmap);
//            bounds = c.getClipBounds();
//            windowBounds = out.getClipBounds();
//
//            if(!started){
//                started = true;
//                months= getBallVector();
//
//                paint= new Paint();
//                paint.setColor(Color.parseColor("#00ff00"));
//                paint.setStrokeWidth(5);
//            }
//
//            for(IBall ball :months){
//                //ball.Update();  // the months don't need to be updated
//                ball.Draw(c);
//            }
//
//            out.drawBitmap(bitmap,c.getClipBounds(),out.getClipBounds(),paint);
//
//
//            for(int i = 0; i < userSequenceTest.size(); i++)
//            {
//                if(demoSequence.get(i) != userSequenceTest.get(i))
//                {
//                    sequencesMatch = false;
//                }
//
//                if(sequencesMatch == false)
//                {
//                    break;
//                }
//
//            }
//
//            // if we've pressed more circles then are in the sequence, then we have failed.
//            if(monthPressedCount > getDemoSequence().size() || sequencesMatch == false)
//            {
//                // return true when finished
//                setResult(true);
//                return true;
//            }
//            // if the sequences match and we have pressed the number of months in the demo sequence
//            else if(sequencesMatch == true && monthPressedCount == demoSequence.size()){
//                // then we have succeded and return true because we have finished
//                setResult(true);
//                return true;
//            } else {
//                return false;
//            }
//
//
//
//        }
//
//
//        public void onTouch(MotionEvent e){
//            switch(e.getAction()){
//                case MotionEvent.ACTION_DOWN:
//                    // figure out if a ball has been touched
//                    Point touchCoord = new Point((int) (e.getX()*(bounds.width()*1.0/windowBounds.width())),(int) (e.getY()*(bounds.height()*1.0/windowBounds.height())));
//
//                    for(IBall ball :months)
//                    {
//                        // if the places that we touched happens to be in the area of the ball, then set ball touch to true
//                        if(ball.contains(touchCoord))
//                        {
//                            ball.setPressed(true);
//                            // add ball pressed to userSequenceTest, so that we can compare with demoSequence
//                            userSequenceTest.add(ball);
//
//                            monthPressedCount++;
//                        }
//                        else // if we didn't touch the ball
//                        {
//                            // then set all the months to false
//                            ball.setPressed(false);
//                        }
//
//                    }
//                    break;
//
//                case MotionEvent.ACTION_UP:
//                    for(IBall ball: months)
//                    {
//                        ball.setPressed(false);
//                    }
//                    break;
//            }
//            // if it has, add it to the users sequence
//            //if(count<clips.size()&&clips.get(count) instanceof ITouchClip)((ITouchClip) clips.get(count)).onTouch(e);
//            //return true;
//        }
//
//
//    };


}
