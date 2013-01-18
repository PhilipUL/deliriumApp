package skm.android.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import skm.android.ViewModle.ViewModles.main.SpaceCogViewViewModle;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 27/09/12
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
public class SpaceCogView extends View {

    private static SpaceCogView instance = null;

    SpaceCogViewViewModle modle;


    public SpaceCogView(Context context) {
        super(context);
        this.instance = this;
        SpaceCogViewViewModle.getInstance(context, null).clear();
        modle = SpaceCogViewViewModle.getInstance(context, this);
        /*BallViewViewModle.getInstance(context, null).clear();

        modle = BallViewViewModle.getInstance(context,this);*/
    }

    public static SpaceCogView getInstance(Context c, Intent intent){

        return instance;
    }

    public SpaceCogView(Context context, AttributeSet attrs){
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
