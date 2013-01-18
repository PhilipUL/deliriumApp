package skm.android;

import android.os.Bundle;
import android.view.View;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 11/10/12
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class Config_screen extends ActivityBase {

    private  static Config_screen config_screen;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Config_screen.config_screen = this;
        //setContentView(R.layout.config_screen);
    }
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//
//        BallViewModle2D modle = BallViewModle2D.getInstance(this.getApplication(), getIntent()) ;
//
//        Binder.init(this.getApplication());
//        Binder.setAndBindContentView(this, R.layout.config_screen, modle);
//
//
//    }
    public void initText()
    {
        Vector<View> elements = new Vector<View>();
        initText(elements);
    }
}