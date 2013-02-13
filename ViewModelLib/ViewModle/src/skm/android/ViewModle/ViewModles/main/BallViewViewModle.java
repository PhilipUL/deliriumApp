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
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: 0884588
 * Date: 01/03/12
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class BallViewViewModle extends ViewModleBase implements Serializable {
    Boolean preview = false;

    public void setPreviewMode() {
        //To change body of created methods use File | Settings | File Templates.
        preview = true;
        clips  = new Vector<IClip>();
        addClip(clips,test,1);



    }

    interface IClip {
         boolean play(Canvas c, Context context);
     }
     interface ITouchClip extends IClip{
         void onTouch(MotionEvent e);
     }
    List<IClip> clips;
    View view;
    private int bounceCount =0;
    public int getBounceCount(){return bounceCount;}
    private static BallViewViewModle instance=null;
    //has a bounce count been specified in the default.xml
    private boolean bounceCountSpecified;
    // what is the number of bounces specified
    private int specifiedNoBounceCount;

    // do we want distractor balls
    private boolean distractorBalls;
    private boolean randomise;
    private float distractorBallsMax;
    private float distractorBallsMin;
    private String targetColour;
    private String distractorColour;

    private float targetMaxSpeed;
    private float targetMinSpeed;
    private float targetBallSpeed;
    private float distractionBallSpeed;


    protected BallViewViewModle(Context c, View view){
        super(c);
        this.view=view;
        instance=this;
        init();

    }
    public static BallViewViewModle getInstance(Context c,View v){
           return instance!=null?instance:(instance=new BallViewViewModle(c,v));
       }

     public void clear(){
           instance=null;
     }


     public void  init() {
         //String answer = Shared.getOptionAtribute(getString(R.string.distractBalls), getString(R.string.distract), this);
         distractorBalls = Boolean.valueOf(Shared.getOptionAtribute(getString(R.string.distractBalls), getString(R.string.distract), this));
         randomise = Boolean.valueOf(Shared.getOptionAtribute(getString(R.string.BallRadio), getString(R.string.checked), this));
         targetColour = Shared.getOptionAtribute(getString(R.string.ballColours), getString(R.string.target), this);
         distractorColour = Shared.getOptionAtribute(getString(R.string.ballColours), getString(R.string.distractor), this);

         targetBallSpeed = Float.parseFloat(Shared.getOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetSpeedBall), this))/10000;
         distractionBallSpeed = Float.parseFloat(Shared.getOptionAtribute(getString(R.string.DistractionTargetSpeed), getString(R.string.Speed), this))/10000;

         distractorBallsMax =  Float.parseFloat(Shared.getOptionAtribute(getString(R.string.DistractionTargetSpeed), getString(R.string.MaxSpeed), this))/10000;
         distractorBallsMin =  Float.parseFloat(Shared.getOptionAtribute(getString(R.string.DistractionTargetSpeed), getString(R.string.MinSpeed), this))/10000;

         targetMaxSpeed =  Float.parseFloat(Shared.getOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetMaxSpeed), this))/10000;
         targetMinSpeed =  Float.parseFloat(Shared.getOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetMinSpeed), this))/10000;



         specifiedNoBounceCount = Integer.decode(Shared.getOptionAtribute(this.getString(R.string.balltestkey), this.getString(R.string.ballcount), this));
         if(specifiedNoBounceCount > 1)
         {
//             if(specifiedNoBounceCount > 10)
//             {
//                 System.out.println("It's true");
//             }
             bounceCountSpecified = true;
         } else
         {
             bounceCountSpecified = false;
         }

         clips  = new Vector<IClip>();
         addClip(clips,start,1);
         addClip(clips,test,1);
         addClip(clips,end,1);

     }
     private void addClip(List<IClip> clips,IClip clip,int times){
         for(int i=0;i<times;i++) clips.add(clip);
     }

    private int count = 0;

     public void onDraw(Canvas c){
         if(count<clips.size()&&clips.get(count).play(c,getApplicationContext()))count++;
     }

     // the start clip just displays the message "tap the screen to start the test...", the start clip finishes, by passing a boolean, when the onTouch event is triggered
     private IClip start = new ITouchClip(){
         boolean finished = false;
         Paint paint = null;

         public boolean play(Canvas c,Context context){
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
             c.drawText("ball bounces",50,300,paint);
             return finished;
         }

         public void onTouch(MotionEvent e) {
             if(preview != true)
             {
                  finished=true;
             }
         }
     };

     private IClip end = new IClip(){
         boolean finished = false;
         Paint paint = null;

         public boolean play(Canvas c,Context context){
             if(paint==null){
                 String currentSize = Shared.getOptionAtribute(context.getString(R.string.FontSize), getString(R.string.current), context);
                 String textColour = Shared.getOptionAtribute(context.getString(R.string.Colours), getString(R.string.text), context);
                 paint=new Paint();
                 paint.setColor(Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" + textColour, context).getTextContent()));
                 //paint.setStrokeWidth(5);
                 paint.setTextSize(new Integer(Shared.getOption(context.getString(R.string.FontSize) +"/"+ currentSize,context).getTextContent()));

             }
             c.drawText("please enter",50,50,paint);
             c.drawText("the number of ",50,100,paint);
             c.drawText("times the pink",50,150,paint);
             c.drawText("ball bounced below",50,200,paint);
             //c.drawText("the total bounce",50,250,paint);
             //c.drawText("count was : "+getBounceCount(),50,300,paint);


             return finished;
         }


     };




     private IClip test = new IClip()
     {
         boolean finished = false;
         Paint paint = null;
         boolean started =false;
         long start,current,end;
         Vector<IBall> balls;
         Rect bounds ;
         Ball target;

         public boolean play(Canvas out,Context context)
         {
             float aspect = out.getHeight()*1.0f/out.getWidth();
             int width = 1000;
             Bitmap bitmap = Bitmap.createBitmap(width,Math.round(width*aspect), Bitmap.Config.RGB_565);
             Canvas c = new Canvas(bitmap);

             if(!started){

                  started=true;
                  start= System.currentTimeMillis();
//
//                  end=start +15000;
//                 10000+random.nextInt(5000);
                 Random random = new Random();

                 // ball should bounce for 12-17 seconds
                  end=start + 12000+random.nextInt(5000);

                  balls=new Vector<IBall>();
                  bounds=c.getClipBounds();


                    // 50 should be the height devided by constant,
                  if(randomise == true)
                  {
                      int temp;
                      if(targetMinSpeed > targetMaxSpeed)
                      {
                          temp = (int)((targetMinSpeed - targetMaxSpeed)*50);
                      } else {
                          temp = (int)((targetMaxSpeed - targetMinSpeed)*50);
                      }

                      int temp2 = (int)(targetMinSpeed*50);
                      int velocity = random.nextInt(temp)+(temp2);

                    target = new Ball(new Point(bounds.width()/2,10+(2+random.nextInt(10))*(1+random.nextInt(10))),Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" +targetColour,context).getTextContent()),45,bounds);
                    target.setInitialVelosity(new Point(0, velocity));
                  } else {
                      target = new Ball(new Point(bounds.width()/2,10+(2+random.nextInt(10))*(1+random.nextInt(10))),Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" +targetColour,context).getTextContent()),45,bounds);
                      float temp = 50*targetBallSpeed;
                      target.setInitialVelosity(new Point(0, (int) temp));
                  }

                  balls.add(target);
                  if(distractorBalls == true)
                  {
                      int velocity1 = 0;
                      int velocity2 = 0;
                      if(randomise == true)
                      {
                          int temp;
                          if(distractorBallsMin > distractorBallsMax)
                          {
                              temp = (int)((distractorBallsMin - distractorBallsMax)*50);
                          } else {
                              temp = (int)((distractorBallsMax - distractorBallsMin)*50);
                          }

                          int temp2 = (int)(distractorBallsMin*50);
                          velocity1 = random.nextInt(temp)+(temp2);



//                          if(random.nextInt(2) == 0)// 50 50 chance
//                          {
//                              velocity1 = (target.getVelosity().y + 10) + random.nextInt(10); // the distractor velocity for ball 1, must be at least 5 units faster, then randomly in a range from 0-5
//                          }else{
//                              velocity1 = (target.getVelosity().y - 10) - random.nextInt(10); // the distractor velocity for ball 1, must be at least 5 units slower, then randomly in a range from 0-5
//                              if(velocity1 <= 0)
//                              {
//                                  velocity1 = target.getVelosity().y/2;
//                              }
//                          }
                      } else {

                          velocity1 = (int) (60*distractionBallSpeed);
                      }

                      if(randomise == true)
                      {
                          int temp;
                          if(distractorBallsMin > distractorBallsMax)
                          {
                              temp = (int)((distractorBallsMin - distractorBallsMax)*50);
                          } else {
                              temp = (int)((distractorBallsMax - distractorBallsMin)*50);
                          }

                          int temp2 = (int)(distractorBallsMin*50);
                          velocity2 = random.nextInt(temp)+(temp2);
                      } else {
                          velocity2 = (int) (60*distractionBallSpeed);
                      }


                      IBall ball1 = new Ball(new Point(bounds.width()/4,10+(2+random.nextInt(10))*(1+random.nextInt(10))),Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" +distractorColour,context).getTextContent()),45,bounds);
                      ball1.setInitialVelosity(new Point(0, velocity1));

                      IBall ball2 = new Ball(new Point(3 * bounds.width() / 4, 10 + (2 + random.nextInt(10)) * (1 + random.nextInt(10))), Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" +distractorColour,context).getTextContent()), 45, bounds);
                      ball2.setInitialVelosity(new Point(0, velocity2));

                      balls.add(ball1);
                      balls.add(ball2);

                  }
                  paint= new Paint();
                  paint.setColor(Color.parseColor("#00ff00"));
                  paint.setStrokeWidth(5);
              }
             c.drawLine(bounds.left,bounds.bottom-1,bounds.right,bounds.bottom-1,paint);
             c.drawLine(bounds.left,bounds.top+1,bounds.right,bounds.top+1,paint);
              current=System.currentTimeMillis();
             for(IBall ball :balls){
                 ball.Update();
                 ball.Draw(c);
             }



             bounceCount=target.getCount();

             out.drawBitmap(bitmap,c.getClipBounds(),out.getClipBounds(),paint);

             if(bounceCountSpecified == false)
             {
                if(current>end)
                {
                    return true;
                }else
                {
                    return false;
                }
                //return current>end  ;
             } else {

                 if(bounceCount == specifiedNoBounceCount)
                 {
                     //if(Math.abs(target.getVelosity().y) < 4)
                     if(target.getCenter().y < bounds.height()/4)
                     {
                        return true;
                     }
                     else
                     {
                         return false;
                     }
                 } else {
                     return false;
                 }

             }

         }


     };



     public boolean onTouchEvent(MotionEvent e){
         if(count<clips.size()&&clips.get(count) instanceof ITouchClip)((ITouchClip) clips.get(count)).onTouch(e);
         return true;
     }


}
