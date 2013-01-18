package skm.android.ViewModle.ViewModles.main;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import skm.android.R;
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
public class LightHouseViewViewModle  extends ViewModleBase implements Serializable {
    interface IClip {
         boolean play(Canvas c,Context context);
     }
    interface IDrawClip extends IClip{
        void draw(Canvas c);
    }
    interface ITouchClip extends IClip{
         void onTouch(MotionEvent e);
     }
    List<IClip> clips;
    View view;
    private int flashcount =0;
    public int getFlashcount(){return flashcount;}
    private static LightHouseViewViewModle instance=null;

    private boolean flashesSpecified;
    private int noFlashesSpecified;

    protected LightHouseViewViewModle(Context c,View view)
    {
        super(c);
        this.view=view;
        instance=this;
        init();

    }
    public static LightHouseViewViewModle getInstance(Context c,View v){
           return instance!=null?instance:(instance=new LightHouseViewViewModle(c,v));
       }

     public static void clear(){
           instance=null;
     }


     public void  init() {

         noFlashesSpecified = Integer.parseInt(Shared.getOptionAtribute(getString(R.string.lighthouseflashkey), getString(R.string.flashcount), this));
         if(noFlashesSpecified > 0)
         {
             flashesSpecified = true;
         } else
         {
             flashesSpecified = false;
         }

         //bmp = BitmapFactory.decodeResource(getResources(), R.drawable.lighthouse_pannel);
         //bmp = BitmapFactory.decodeResource(getResources(), R.drawable.simple_lighthouse_pannel);
         BitmapFactory.Options options = new BitmapFactory.Options();
         options.inSampleSize=Shared.SUBSAMPLE;
         //bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon,options);
         clips  = new Vector<IClip>();
         Random random = new Random(System.currentTimeMillis());

         if(flashesSpecified == false)
         {
             int rand = random.nextInt(6);
             for(int i=0;i<5+rand;i++){
                 if(clips.size()==0)
                 {
//                     addClip(clips,start,1);
//                     addClip(clips,wait,4);
//                     //addClip(clips,flash,1+random.nextInt(2));
//                     addClip(clips,flash,4);
//                     //addClip(clips,wait,4);
//                     addClip(clips, new Prompt(), 1);
//
//                     addClip(clips,flash,3);
//                     //addClip(clips,wait,4);
//                     addClip(clips, new Prompt(), 1);
//                     addClip(clips,flash,6);

                     addClip(clips,start,1);
                     addClip(clips,wait,4);
                     //addClip(clips,flash,1+random.nextInt(2));
                     addClip(clips,frontFlash,4);
                     //addClip(clips,wait,4);
                     addClip(clips, new Prompt(), 1);

                     addClip(clips,frontFlash,3);
                     //addClip(clips,wait,4);
                     addClip(clips, new Prompt(), 1);
                     addClip(clips,frontFlash,6);


                     flashcount = 13;
                 }
//             if(clips.get(clips.size()-1)==flash&& !(clips.get(clips.size()-1)==wait&&clips.get(clips.size()-2)==wait&&clips.get(clips.size()-3)==wait&& clips.get(clips.size()-4)==wait))addClip(clips,wait,2);
//                 {
                 if(clips.get(clips.size()-1)==frontFlash&& !(clips.get(clips.size()-1)==wait&&clips.get(clips.size()-2)==wait&&clips.get(clips.size()-3)==wait&& clips.get(clips.size()-4)==wait))addClip(clips,wait,2);
                 {

                     addClip(clips,wait,2);
                 }

             }
         } else {

             addClip(clips,start,1);
             addClip(clips,wait,4);
             addClip(clips,flash,noFlashesSpecified);

             flashcount = noFlashesSpecified;
//             if(clips.size()==0)
//             {
//                     addClip(clips,start,1);
//                     addClip(clips,wait,4);
//                     addClip(clips,flash,noFlashesSpecified);
//
//                     flashcount++;
//             }

             /// if the last clip is a flash and not a wait, the second last clip is a wait, the third last clip is a wait,and the fourth last clip is a wait
//             if(clips.get(clips.size()-1)==flash && !(clips.get(clips.size()-1)== wait && clips.get(clips.size()-2) == wait && clips.get(clips.size()-3)== wait && clips.get(clips.size()-4)==wait))
//             {
//                 ///then add a wait 2 times
//                 addClip(clips,wait,2);
//             }
         }

         addClip(clips,end,1);


     }


     private void addClip(List<IClip> clips,IClip clip,int times){
         for(int i=0;i<times;i++)
         {
             clips.add(clip);
         }
     }

     private Bitmap bmp;

     private int count = 0;

     public void onDraw(Canvas c)
     {
         //if(bmp==null)bmp = BitmapFactory.decodeResource(getResources(), R.drawable.lighthouse_pannel);

         //if(bmp ==null)bmp = BitmapFactory.decodeResource(getResources(), R.drawable.simple_lighthouse_pannel);
         BitmapFactory.Options options = new BitmapFactory.Options();
         options.inSampleSize=Shared.SUBSAMPLE;
         options.inDither=true;
         options.inScreenDensity = c.getDensity();
         //if(bmp ==null)bmp = BitmapFactory.decodeResource(getResources(), R.drawable.lighthouse20frames,options);

         if(count<clips.size()&&clips.get(count).play(c,getApplicationContext()))
         {
             count++;
         }
     }

    private IClip frontFlash = new IClip()
    {
        long currentTime=System.currentTimeMillis() ,lastTime=System.currentTimeMillis();
        private  int count =0;
        int lightpoint1X = 300;
        int lightpoint1Y = 150;
        int lightpoint2X = 300;
        int lightpoint2Y = 150;
        int radius = 0;

        int lightXpoints[] = {350, lightpoint1X , lightpoint2X};
        int lightYpoints[] = {150, lightpoint1Y, lightpoint2Y};

        Vector<Integer> lightVector = new Vector<Integer>(2,2);
//        int x2Points[] = {200, 400, 360, 500, 340, 340, 260, 260, 240};
//        int y2Points[] = {600, 600, 200, 200, 200, 100, 100, 200, 200};

//        int x2Points[] = {200, 400, 360, 385, 385, 375, 375, 360, 360, 380, 380, 300, 220, 220, 240, 240, 210, 210, 200, 200, 240 };
//        int y2Points[] = {600, 600, 200, 200, 180, 180, 190, 190, 100, 100, 90,  80,  90,  100, 100, 190, 190, 180, 180, 200, 200};

        int x2Points[] = {200, 400, 360, 385, 385, 375, 375, 360, 360, 380, 380, 300, 220, 220, 240, 240, 180, 180, 170, 170, 240 };
        int y2Points[] = {600, 600, 200, 200, 180, 180, 190, 190, 100, 100, 90,  80,  90,  100, 100, 190, 190, 180, 180, 200, 200};

        Paint paint = new Paint();
        int flashCountTracker = 0;
        boolean rightFlash = false;

        int lightSpeedLateral = 50;
        int lightSpeedVirtical = 40;

        public boolean play(Canvas c,Context context)
        {

            Path lighthousePath = new Path();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.RED);

            lighthousePath.moveTo (x2Points[0], y2Points[0]);

            for (int index = 1; index < x2Points.length; index++) {
                lighthousePath.lineTo(x2Points[index], y2Points[index]);
            };
            Matrix translate = new Matrix();
            translate.setTranslate(330,100);
            lighthousePath.transform(translate);

            c.drawPath(lighthousePath, paint);

            paint.setColor(Color.YELLOW);
            c.drawCircle(635, 230, radius, paint);

            if(radius < 800)
            {
                radius+= 5;
                if(radius > 300)
                {
                    radius+=5;
                }
                return false;
            } else{
                return true;
            }




        }

    };

    private IClip flash = new IClip()
    {
        long currentTime=System.currentTimeMillis() ,lastTime=System.currentTimeMillis();
        private  int count =0;
        int lightpoint1X = 300;
        int lightpoint1Y = 150;
        int lightpoint2X = 300;
        int lightpoint2Y = 150;

        int lightXpoints[] = {350, lightpoint1X , lightpoint2X};
        int lightYpoints[] = {150, lightpoint1Y, lightpoint2Y};

        Vector<Integer> lightVector = new Vector<Integer>(2,2);
//        int x2Points[] = {200, 400, 360, 500, 340, 340, 260, 260, 240};
//        int y2Points[] = {600, 600, 200, 200, 200, 100, 100, 200, 200};

//        int x2Points[] = {200, 400, 360, 385, 385, 375, 375, 360, 360, 380, 380, 300, 220, 220, 240, 240, 210, 210, 200, 200, 240 };
//        int y2Points[] = {600, 600, 200, 200, 180, 180, 190, 190, 100, 100, 90,  80,  90,  100, 100, 190, 190, 180, 180, 200, 200};

        int x2Points[] = {200, 400, 360, 385, 385, 375, 375, 360, 360, 380, 380, 300, 220, 220, 240, 240, 180, 180, 170, 170, 240 };
        int y2Points[] = {600, 600, 200, 200, 180, 180, 190, 190, 100, 100, 90,  80,  90,  100, 100, 190, 190, 180, 180, 200, 200};

        Paint paint = new Paint();
        int flashCountTracker = 0;
        boolean rightFlash = false;

        int lightSpeedLateral = 50;
        int lightSpeedVirtical = 40;
        public boolean play(Canvas c,Context context)
        {

            Path lighthousePath = new Path();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.RED);

            lighthousePath.moveTo (x2Points[0], y2Points[0]);

            for (int index = 1; index < x2Points.length; index++) {
                lighthousePath.lineTo(x2Points[index], y2Points[index]);
            };

            Matrix translate = new Matrix();
            translate.setTranslate(330,100);
            lighthousePath.transform(translate);

            c.drawPath(lighthousePath, paint);

            // -------------------------------------

            Path lightPath = new Path();

            Paint lightPaint = new Paint();
            lightPaint.setStyle(Paint.Style.FILL);
            lightPaint.setColor(Color.YELLOW);



            if(rightFlash == false)
            {
                if(lightpoint1X < 900)
                {

                    lightpoint1X +=lightSpeedLateral;
                    lightpoint2X +=lightSpeedLateral;

                    lightXpoints[1] = lightpoint1X ;
                    lightXpoints[2] = lightpoint2X ;

                    if(lightpoint1X%3 == 0)
                    {
                        lightpoint1Y += 3;
                        lightpoint2Y -= 3;
                        lightYpoints[1] = lightpoint1Y;
                        lightYpoints[2] = lightpoint2Y;

                    }
    //                lightYpoints[1] = ++lightpoint1Y;
    //                lightYpoints[2] = --lightpoint2Y;
                }
                else if( lightpoint2Y > -400)
                {
                    lightpoint1Y += lightSpeedVirtical;
                    lightpoint2Y -= lightSpeedVirtical;

                    lightYpoints[1] = lightpoint1Y;
                    lightYpoints[2] = lightpoint2Y;
                }
                else if (flashCountTracker < 3)
                {
                    // flash
                    Paint yellow = new Paint();
                    yellow.setColor(Color.YELLOW);
                    flashCountTracker++;

                    c.drawRect(0,0,2000,2000, yellow);
                }
                else
                {
                    // reinitialise variables
                    lightpoint1X = -300;
                    lightpoint2X = -300;
                    lightXpoints[1] = lightpoint1X;
                    lightXpoints[2] = lightpoint2X;
                    lightXpoints[0] = 250;

                    rightFlash = true;
                }
            } else {
                if(lightpoint2Y < 106)
                {
                    lightpoint1Y -= lightSpeedVirtical;
                    lightpoint2Y += lightSpeedVirtical;

                    lightYpoints[1] = lightpoint1Y;
                    lightYpoints[2] = lightpoint2Y;
                }
                else if(lightpoint1X < 300)
                {

                    lightpoint1X +=lightSpeedLateral;
                    lightpoint2X +=lightSpeedLateral;

                    lightXpoints[1] = lightpoint1X ;
                    lightXpoints[2] = lightpoint2X ;

                    if(lightpoint1X%3 == 0)
                    {
                        lightpoint1Y -= 3;
                        lightpoint2Y += 3;
                        lightYpoints[1] = lightpoint1Y;
                        lightYpoints[2] = lightpoint2Y;

                    }
                    //                lightYpoints[1] = ++lightpoint1Y;
                    //                lightYpoints[2] = --lightpoint2Y;
                } else {
                    //reinitalise
                    rightFlash = false;
                    lightpoint1X = 300;
                    lightpoint1Y = 150;
                    lightpoint2X = 300;
                    lightpoint2Y = 150;

                    lightXpoints[0] = 350;
                    lightXpoints[1] = lightpoint1X;
                    lightXpoints[2] = lightpoint2X;

                    lightYpoints[0] = 150;
                    lightYpoints[1] = lightpoint1Y;
                    lightYpoints[2] = lightpoint2Y;

                }
            }

            lightPath.moveTo (lightXpoints[0], lightYpoints[0]);

            for (int index = 1; index < lightYpoints.length; index++) {
                lightPath.lineTo(lightXpoints[index], lightYpoints[index]);
            };
            lightPath.transform(translate);



            while(currentTime-lastTime<1000/30)
            {
                currentTime = System.currentTimeMillis();
            }


            c.drawPath(lightPath, lightPaint);
            count=(count +1)%(56);
            //setFrame(c,getFrame(bmp,count,20,1));
            lastTime=currentTime;
            if(count==0)
            {
                return true;
            }else{
                return false;
            }




        }

    };

     private IClip wait = new IClip(){
             long currentTime=System.currentTimeMillis() ,lastTime =currentTime,endtTime=currentTime+250;
             public boolean play(Canvas c,Context context){
                 if(endtTime<lastTime) endtTime=(lastTime=currentTime=System.currentTimeMillis())+250;
                 while(currentTime-lastTime<50)  currentTime = System.currentTimeMillis();
                 //setFrame(c,getFrame(bmp,0,1,1));
                 return (lastTime=currentTime)>=endtTime;
             }
         };

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
             c.drawText("the test",50,150,paint);
             return finished;
         }

         public void onTouch(MotionEvent e) {

                  finished=true;
         }
     };

    private class Prompt implements ITouchClip
    {
        boolean finished = false;
        Paint paint = null;

        public boolean play(Canvas c,Context context)
        {
            if(paint==null){
                String currentSize = Shared.getOptionAtribute(context.getString(R.string.FontSize), getString(R.string.current), context);
                String textColour = Shared.getOptionAtribute(context.getString(R.string.Colours), getString(R.string.text), context);
                paint=new Paint();
                paint.setColor(Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" + textColour, context).getTextContent()));
                //paint.setStrokeWidth(5);
                paint.setTextSize(new Integer(Shared.getOption(context.getString(R.string.FontSize) +"/"+ currentSize,context).getTextContent()));

            }
            c.drawText("Touch Screen for",50,50,paint);
            c.drawText("the next flash",50,100,paint);
            c.drawText("test",50,150,paint);
            return finished;
        }

        public void onTouch(MotionEvent e) {

            finished=true;
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
             c.drawText("flashes below",50,150,paint);
             return finished;
         }

         public void onTouch(MotionEvent e) {

             finished=true;

         }
     };


     public boolean onTouchEvent(MotionEvent e){
         if(count<clips.size()&&clips.get(count) instanceof ITouchClip)((ITouchClip) clips.get(count)).onTouch(e);
         return true;
     }

     private void setFrame(Canvas c, Rect frame) {
         c.drawBitmap(bmp,frame,new Rect(view.getLeft(),view.getTop(),view.getRight(),view.getBottom()),null);
     }

     public Rect getFrame(Bitmap bitmap,int pos, int horizontalDevisions,int virticalDevisions){
         int startX = Math.round((pos%horizontalDevisions)*(bmp.getWidth()*1.0f/horizontalDevisions)); // fix this for no gitter
         int startY =(pos/horizontalDevisions)*(bmp.getHeight()/virticalDevisions);
         int endX = ((pos%horizontalDevisions)+1)*(bmp.getWidth()/horizontalDevisions);
         int endY = ((pos/horizontalDevisions)+1)*(bmp.getHeight()/virticalDevisions);
         return new Rect(startX,startY,endX,endY);
     }

}
