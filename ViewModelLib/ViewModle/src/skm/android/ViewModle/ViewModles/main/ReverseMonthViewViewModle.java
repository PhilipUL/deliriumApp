package skm.android.ViewModle.ViewModles.main;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import skm.android.R;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.base.ViewModleBase;
import skm.android.ViewModle.ViewModles.rectangle;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

//import skm.android.ViewModle.ViewModles.Ball;
//import skm.android.ViewModle.ViewModles.IBall;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 03/04/13
 * Time: 10:15
 * To change this template use File | Settings | File Templates.
 */
public class ReverseMonthViewViewModle extends ViewModleBase implements Serializable {


        int boxBoundry = (Integer.parseInt(Shared.getOptionAtribute(getString(R.string.ReverseMonthkey), getString(R.string.boxSize), this)))/100;

        int textSize = Math.abs(((Integer.parseInt(Shared.getOptionAtribute(getString(R.string.ReverseMonthkey), getString(R.string.textSize), this)))/100)-100);

        Paint paint = null;
        private String textColourStr;
        private String boxColourStr;


        List<IClip> clips = new Vector<IClip>();
        View view;
        int ballCountSequence;
        Rect windowBounds;
        Vector<rectangle> months;
        double startTime;

        int sequenceNumber = 4;

        long pastTime =0;
        long currentTime =0;

        List<rectangle> demoSequence;
        List<rectangle> userSequence;

        List<String> userSequenceString = new ArrayList<String>();

        public ReverseMonthViewViewModle(Context context, View v) {

            super(context);
            ballCountSequence = 2;
            instance = this;
            this.view = v;

            if(paint==null){

                boxColourStr = Shared.getOptionAtribute(getString(R.string.ReverseMonthkey), getString(R.string.boxColour), this);
                textColourStr = Shared.getOptionAtribute(getString(R.string.ReverseMonthkey), getString(R.string.textColour), this);

//                IBall ball1 = new Ball(new Point(bounds.width()/4,10+(2+random.nextInt(10))*(1+random.nextInt(10))),Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" +distractorColour,context).getTextContent()),45,bounds);
                //Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" +distractorColour,context).getTextContent())

            }

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

        public void setMonthVector(Vector<rectangle> b)
        {
            months = b;
        }

        public Vector<rectangle> getMonthVector()
        {
            return months;
        }

        public List<rectangle> getDemoSequence() {
            return demoSequence;
        }

        public double getStartTime() {
            return startTime;
        }

        public List<String> getUserSequenceString() {
                return userSequenceString;
        }

        public void setUserSequenceString(List<String> userSequenceString) {
            this.userSequenceString = userSequenceString;
        }

        public void setDemoSequence(List<rectangle> demoSequence) {
            this.demoSequence = demoSequence;
        }

        public List<rectangle> getUserSequence() {
            return userSequence;
        }

        public void setUserSequence(List<rectangle> userSequence) {
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
//                    ITestClip tClip = (ITestClip) clips.get(count);
//                    if(tClip.getResult() == true)
//                    {
//                        //addClip(clips, new Intermediate(),1);
//                        addClip(clips, new Demo(), 1);
//                        addClip(clips,new Intermediate(),1);
//                        addClip(clips,new Test(),1);
//                        //setSequenceNumber(getSequenceNumber()+1);
//
//                    }
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




        private static ReverseMonthViewViewModle instance = null;



        public static ReverseMonthViewViewModle getInstance(Context c,View v){
            return instance!=null?instance:(instance=new ReverseMonthViewViewModle(c,v));
        }

        public void clear(){
            instance=null;
        }

        public void init()
        {



            addClip(clips,start,1);
            //addClip(clips,new Demo(),1);
            //addClip(clips,new Intermediate(),1);

            addClip(clips,new Test(),1);


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
                c.drawText("Pick the Months",50,200,paint);
                c.drawText("in reverse order",50,250,paint);
                //c.drawText("",50,300,paint);

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
            List<rectangle> sequence = new ArrayList<rectangle>();

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
                    months =new Vector<rectangle>();
                    //months.add(new Ball(new Point(bounds.width()/4,10+(2+random.nextInt(10))*(1+random.nextInt(10))),Color.parseColor("#00ff00"),45,bounds));
                    List<Rect> rectList = new ArrayList<Rect>();
                    rectList.add(new Rect(bounds.left, bounds.top, bounds.width() / 4, bounds.height() / 3));
                    rectList.add(new Rect(bounds.width() / 4, bounds.top, bounds.width()*2 / 4, bounds.height() / 3));
                    rectList.add(new Rect(bounds.width()*2 / 4, bounds.top, bounds.width()*3 / 4, bounds.height() / 3));
                    rectList.add(new Rect(bounds.width()*3 / 4, bounds.top, bounds.right, bounds.height() / 3));

                    rectList.add(new Rect(bounds.left, bounds.height() / 3, bounds.width() / 4, bounds.height()*2 / 3));
                    rectList.add(new Rect(bounds.width() / 4, bounds.height() / 3, bounds.width()*2 / 4, bounds.height()*2 / 3));
                    rectList.add(new Rect(bounds.width()*2 / 4, bounds.height() / 3, bounds.width()*3 / 4, bounds.height()*2 / 3));
                    rectList.add(new Rect(bounds.width()*3 / 4, bounds.height() / 3, bounds.right, bounds.height()*2 / 3));

                    rectList.add(new Rect(bounds.left, bounds.height()*2 / 3, bounds.width() / 4, bounds.height()*3 / 3));
                    rectList.add(new Rect(bounds.width() / 4, bounds.height()*2 / 3, bounds.width()*2 / 4, bounds.height()*3 / 3));
                    rectList.add(new Rect(bounds.width()*2 / 4, bounds.height()*2 / 3, bounds.width()*3 / 4, bounds.height()*3 / 3));
                    rectList.add(new Rect(bounds.width()*3 / 4, bounds.height()*2 / 3, bounds.right, bounds.height()*3 / 3));

                    Collections.shuffle(rectList);
                    String boxColour = Shared.getOption(context.getString(R.string.Colours) + "/" + boxColourStr, context).getTextContent();
                    //Color boxColour = Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" +boxColourStr  ));

                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(0), "January", boxBoundry));
                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(1), "February", boxBoundry));
                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(2), "March", boxBoundry));
                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(3), "April", boxBoundry));

                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(4), "May", boxBoundry));
                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(5), "June", boxBoundry));
                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(6), "July", boxBoundry));
                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(7), "August", boxBoundry));

                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(8), "September", boxBoundry));
                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(9), "October", boxBoundry));
                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(10), "November", boxBoundry));
                    months.add(new rectangle(Color.parseColor(boxColour), rectList.get(11), "December", boxBoundry));

                    //Vector v = new Vector();
//                    Iterator itr = months.iterator();
//                    while(itr.hasNext())
//                    {
//                        itr.next().
//                    }

                    String textColour = Shared.getOption(context.getString(R.string.Colours) + "/" + textColourStr, context).getTextContent();
                    for (int i = 0; i < months.size(); i++)
                    {
                        months.get(i).setTextSize(textSize);
                        months.get(i).setTextColour(textColour);
                    }

//                    setSequenceNumber(months.size());
//                    sequenceNumber = getSequenceNumber();
//                    // create a list of random months from the months Vector. SequenceNumber dictates how many random months will be stored in the sequence variable
//                    for(int i = 0; i< sequenceNumber; i++)
//                    {
//                        sequence.add(months.get(i));
//                    }

                    // NB
                    // set the SpaceCogViewViewModle's sequence variable
                    setDemoSequence(sequence);

                    setMonthVector(months);
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
                c.drawText("Pick the Months",50,200,paint);
                c.drawText("in reverse order",50,250,paint);

                out.drawBitmap(bitmap,c.getClipBounds(),out.getClipBounds(),paint);

                return finished;
            }

            public void onTouch(MotionEvent e) {

                finished=true;
                java.util.Date date= new java.util.Date();
                currentTime = new Timestamp(date.getTime()).getTime();
                startTime = currentTime;
            }
        };



        private class Test implements ITestClip
        {
            int monthPressedCount = 0;
            boolean started =false;
            Vector<rectangle> months;
            Rect bounds ;
            boolean sequencesMatch = true;
            List<rectangle> userSequenceTest = new ArrayList<rectangle>();

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
                    months= getMonthVector();

                    paint= new Paint();
                    paint.setColor(Color.parseColor("#00ff00"));
                    paint.setStrokeWidth(5);
                }

                for(rectangle month :months){
                    //ball.Update();  // the months don't need to be updated
                    month.Draw(c);
                }

                out.drawBitmap(bitmap,c.getClipBounds(),out.getClipBounds(),paint);


//                for(int i = 0; i < userSequenceTest.size(); i++)
//                {
//                    if(demoSequence.get(i) != userSequenceTest.get(i))
//                    {
//                        sequencesMatch = false;
//                    }
//
//                    if(sequencesMatch == false)
//                    {
//                        break;
//                    }
//
//                }


                return false;

//                // if we've pressed more circles than are in the sequence, then we have failed.
//                if(monthPressedCount > getDemoSequence().size() || sequencesMatch == false)
//                {
//                    // return true when finished
//                    for(rectangle month: months)
//                    {
//                        month.setPressed(false);
//                    }
//                    setResult(false);
//                    return true;
//                }
//                // if the sequences match and we have pressed the number of months in the demo sequence
//                else if(sequencesMatch == true && monthPressedCount == demoSequence.size()){
//                    // then we have succeded and return true because we have finished
//
//                    setResult(true);
//                    return true;
//                } else {
//                    return false;
//                }



            }


            public void onTouch(MotionEvent e){
                if(months != null)
                {


                    switch(e.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            // figure out if a ball has been touched
                            //
                            Point touchCoord = new Point((int) (e.getX()*(bounds.width()*1.0/windowBounds.width())),(int) (e.getY()*(bounds.height()*1.0/windowBounds.height())));

                            for(rectangle month:months)
                            {
                                // if the places that we touched happens to be in the area of the ball, then set ball touch to true
                                if(month.contains(touchCoord))
                                {
                                    month.setPressed(true);
    //                            // add ball pressed to userSequenceTest, so that we can compare with demoSequence
    //                            userSequenceTest.add(ball);
    //
    //                            monthPressedCount++;
                                }
                                else // if we didn't touch the ball
                                {
                                    // then set all the months to false
                                    month.setPressed(false);
                                }

                            }
                            break;

                        case MotionEvent.ACTION_UP:

                            for(rectangle month: months)
                            {
                                if(month.isPressed() == true)
                                {
                                    // add ball pressed to userSequenceTest, so that we can compare with demoSequence
                                    java.util.Date date= new java.util.Date();
                                    pastTime = currentTime;
                                    currentTime = new Timestamp(date.getTime()).getTime();

                                    double timeDiff = ((double)currentTime-(double)pastTime)/1000;
                                    //float timeDiffF = ((float)currentTime-(float)pastTime)/1000;
                                    userSequenceTest.add(month);
                                    userSequenceString.add(month.getMonth() + "  time taken = " + timeDiff);

                                    monthPressedCount++;
                                    month.setPressed(false);
                                }
                            }
                            break;
                    }
                }
                // if it has, add it to the users sequence
                //if(count<clips.size()&&clips.get(count) instanceof ITouchClip)((ITouchClip) clips.get(count)).onTouch(e);
                //return true;
            }
        }





}
