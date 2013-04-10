package skm.android;

import android.os.Bundle;
import android.view.View;
import gueei.binding.Binder;
import skm.android.ViewModle.ViewModles.main.ReverseMonthViewModle;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: philip
 * Date: 25/03/13
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class Reverse_Month extends ActivityBase {

    /** Called when the activity is first created. */
    private static Reverse_Month reverseMonthObject;


    public static Reverse_Month getCurrentInstance(){
        return reverseMonthObject;
        //menu==null?(menu=new Menu()):menu;
    }

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //lighthouse =this;

        ReverseMonthViewModle modle = ReverseMonthViewModle.getInstance(this.getApplicationContext(), getIntent());

        //SpaceCogView modle = SpaceCogView.getInstance(this.getApplicationContext(), getIntent());
        Binder.init(this.getApplication());
        //Binder.setAndBindContentView(this,R.layout.space_cog, modle);
        Binder.setAndBindContentView(this,R.layout.reverse_month_submit, modle);

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
