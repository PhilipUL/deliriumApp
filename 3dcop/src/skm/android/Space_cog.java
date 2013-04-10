package skm.android;

import android.os.Bundle;
import android.view.View;
import gueei.binding.Binder;
import skm.android.ViewModle.ViewModles.main.BallViewModle2D;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 27/09/12
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */


public class Space_cog extends ActivityBase {

   /* private static Ball lighthouse;

    public static Ball getCurrentInstance(){
        return lighthouse;
        //menu==null?(menu=new Menu()):menu;
    }*/
    /** Called when the activity is first created. */
    @Override

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //lighthouse =this;

        BallViewModle2D modle = BallViewModle2D.getInstance(this.getApplicationContext(), getIntent());

        //SpaceCogView modle = SpaceCogView.getInstance(this.getApplicationContext(), getIntent());
        Binder.init(this.getApplication());
        //Binder.setAndBindContentView(this,R.layout.space_cog, modle);
        Binder.setAndBindContentView(this,R.layout.space_cog, modle);

        /*Spinner spinner = (Spinner) findViewById(R.id.bounceCount);
        List<String> temp = new ArrayList<String>();
        for(int i=1; i<100; i++)temp.add(""+i);
        bindSpinner(spinner, temp, modle.bounceCounterListener, getApplicationContext());*/

    }




    @Override
    public void initText() {
        Vector<View> elements = new Vector<View>();
        //elements.add(findViewById(R.id.submitButton));
        //elements.add(findViewById(R.id.bounceCount));
        initText(elements);
    }
}
