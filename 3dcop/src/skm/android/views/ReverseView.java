package skm.android.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import skm.android.ViewModle.ViewModles.main.ReverseMonthViewViewModle;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 03/04/13
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class ReverseView extends View {

    private static ReverseView instance = null;

    ReverseMonthViewViewModle modle;


    public ReverseView(Context context) {
        super(context);
        this.instance = this;
        ReverseMonthViewViewModle.getInstance(context, null).clear();
        modle = ReverseMonthViewViewModle.getInstance(context, this);
        /*BallViewViewModle.getInstance(context, null).clear();

        modle = BallViewViewModle.getInstance(context,this);*/
    }

    public static ReverseView getInstance(Context c, Intent intent){

        return instance;
    }

    public ReverseView(Context context, AttributeSet attrs){
        this(context);
    }

    @Override
    protected void onDraw(Canvas c){
        super.onDraw(c);
        modle.onDraw(c);
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent e){
        return modle.onTouchEvent(e);
    }
}
