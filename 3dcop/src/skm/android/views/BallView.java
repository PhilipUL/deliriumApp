package skm.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import skm.android.ViewModle.ViewModles.main.BallViewViewModle;


/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 05/01/12
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class BallView extends View {

    BallViewViewModle modle;
    public BallView(Context context) {
        super(context);
        BallViewViewModle.getInstance(context, null).clear();
        modle = BallViewViewModle.getInstance(context,this);
    }

    public BallView(Context context, AttributeSet attrs){
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

