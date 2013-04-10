package skm.android.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import skm.android.ViewModle.ViewModles.main.LightHouseViewModle2D;
import skm.android.ViewModle.ViewModles.main.LightHouseViewViewModle;


/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 05/01/12
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class LighthouseView extends View {
//    interface IClip {
//        boolean play(Canvas c,Context context);
//    }
//    interface ITouchClip extends IClip{
//        void onTouch(MotionEvent e);
//    }
//
//    List<IClip> clips;
    LightHouseViewViewModle modle;
    public LighthouseView(Context context) {
        super(context);
        LightHouseViewViewModle.clear();

        modle = LightHouseViewViewModle.getInstance(context,this);
        modle.setUseSequence(LightHouseViewModle2D.useSequence);

//        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.lighthouse_pannel);
//        clips  = new Vector<IClip>();
//        Random random = new Random(System.currentTimeMillis());
//        int rand = random.nextInt(6);
//        for(int i=0;i<5+rand;i++){
//            if(clips.size()==0){
//                addClip(clips,start,1);
//                addClip(clips,wait,4);
//                addClip(clips,flash,1+random.nextInt(2));
//            }
//            if(clips.get(clips.size()-1)==flash&& !(clips.get(clips.size()-1)==wait&&clips.get(clips.size()-2)==wait&&clips.get(clips.size()-3)==wait&& clips.get(clips.size()-4)==wait))addClip(clips,wait,2);
//            if (random.nextInt(2)==1){
//               // if(random.nextInt()%random.nextInt()==0)addClip(clips,flash,2);
//                //else
//                addClip(clips,flash,1);
//            }
//            else addClip(clips,wait,2+random.nextInt(5));
//        }
//
//        addClip(clips,end,1);


    }
//    private void addClip(List<IClip> clips,IClip clip,int times){
//        for(int i=0;i<times;i++) clips.add(clip);
//    }
    public LighthouseView(Context context, AttributeSet attrs){
        this(context);
    }
//    private  Bitmap bmp;
//
//    private int count = 0;
    @Override
    protected void onDraw(Canvas c){
        super.onDraw(c);
//        if(bmp==null)bmp = BitmapFactory.decodeResource(getResources(), R.drawable.lighthouse_pannel);
//        if(count<clips.size()&&clips.get(count).play(c,getContext()))count++;
//        invalidate();
//        }
//        float aspect = c.getHeight()*1.0f/c.getWidth();
//        int width = 1000;
        Bitmap bitmap = Bitmap.createBitmap(800,600, Bitmap.Config.RGB_565);
        Canvas out = new Canvas(bitmap);
//        c.drawRect(getLeft(), getTop() + p.getStrokeWidth()/2, getRight(), getBottom(), p);
         modle.onDraw(out);
        c.drawBitmap(bitmap,out.getClipBounds(),c.getClipBounds(),new Paint());
        invalidate();
    }

//    private IClip flash = new IClip(){
//            long currentTime=System.currentTimeMillis() ,lastTime=System.currentTimeMillis();
//            private  int count =0;
//            public boolean play(Canvas c,Context context){
//                while(currentTime-lastTime<1000/8)  currentTime = System.currentTimeMillis();
//                count=(count +1)%(4*2);
//                setFrame(c,getFrame(bmp,count,4,2));
//                lastTime=currentTime;
//                return count==0;
//            }
//        };
//    private IClip wait = new IClip(){
//            long currentTime=System.currentTimeMillis() ,lastTime =currentTime,endtTime=currentTime+250;
//            public boolean play(Canvas c,Context context){
//                if(endtTime<lastTime) endtTime=(lastTime=currentTime=System.currentTimeMillis())+250;
//                while(currentTime-lastTime<50)  currentTime = System.currentTimeMillis();
//                setFrame(c,getFrame(bmp,0,4,2));
//                return (lastTime=currentTime)>=endtTime;
//            }
//        };
//
//    private IClip start = new ITouchClip(){
//        boolean finished = false;
//        Paint paint = null;
//
//        public boolean play(Canvas c,Context context){
//            if(paint==null){
//                String currentSize = Shared.getOptionAtribute(context.getString(R.string.FontSize), "current", context);
//                String textColour = Shared.getOptionAtribute(context.getString(R.string.Colours), "text", context);
//                paint=new Paint();
//                paint.setColor(Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" + textColour, context).getTextContent()));
//                //paint.setStrokeWidth(5);
//                paint.setTextSize(new Integer(Shared.getOption(context.getString(R.string.FontSize) +"/"+ currentSize,context).getTextContent()));
//
//            }
//            c.drawText("tap the screen",50,50,paint);
//            c.drawText("to start",50,100,paint);
//            c.drawText("the test",50,150,paint);
//            return finished;
//        }
//
//        public void onTouch(MotionEvent e) {
//
//                 finished=true;
//        }
//    };
//
//    private IClip end = new IClip(){
//        boolean finished = false;
//        Paint paint = null;
//
//        public boolean play(Canvas c,Context context){
//            if(paint==null){
//                String currentSize = Shared.getOptionAtribute(context.getString(R.string.FontSize), "current", context);
//                String textColour = Shared.getOptionAtribute(context.getString(R.string.Colours), "text", context);
//                paint=new Paint();
//                paint.setColor(Color.parseColor(Shared.getOption(context.getString(R.string.Colours) + "/" + textColour, context).getTextContent()));
//                //paint.setStrokeWidth(5);
//                paint.setTextSize(new Integer(Shared.getOption(context.getString(R.string.FontSize) +"/"+ currentSize,context).getTextContent()));
//
//            }
//            c.drawText("please enter",50,50,paint);
//            c.drawText("the number of ",50,100,paint);
//            c.drawText("flashes below",50,150,paint);
//            return finished;
//        }

//        public void onTouch(MotionEvent e) {
//
//            finished=true;
//        }
//    };

    @Override
    public boolean onTouchEvent(MotionEvent e){
//        if(count<clips.size()&&clips.get(count) instanceof ITouchClip)((ITouchClip) clips.get(count)).onTouch(e);
//        return true;
        return modle.onTouchEvent(e);
    }

//    private void setFrame(Canvas c, Rect frame) {
//        c.drawBitmap(bmp,frame,new Rect(getLeft(),getTop(),getRight(),getBottom()),null);
//    }
//
//    public Rect getFrame(Bitmap bitmap,int pos, int horizontalDevisions,int virticalDevisions){
//        int startX = Math.round((pos%horizontalDevisions)*(bmp.getWidth()*1.0f/horizontalDevisions));
//        int startY =(pos/horizontalDevisions)*(bmp.getHeight()/virticalDevisions);
//        int endX = ((pos%horizontalDevisions)+1)*(bmp.getWidth()/horizontalDevisions);
//        int endY = ((pos/horizontalDevisions)+1)*(bmp.getHeight()/virticalDevisions);
//        return new Rect(startX,startY,endX,endY);
//    }

}

