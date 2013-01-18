package skm.android;

import android.os.Bundle;
import skm.android.ViewModle.ViewModles.TwoItemScreener.TwoItemScreenerManager;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 18/11/11
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
public class TwoItemScreener extends YesNoActivityBase {

//    private TwoItemScreenerManager modle;
    private static TwoItemScreener twoItemScreener;

    public static TwoItemScreener getCurrentInstance(){
       return twoItemScreener;
        //menu==null?(menu=new Menu()):menu;
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        twoItemScreener =this;
        modle = TwoItemScreenerManager.getInstance(this.getApplicationContext(),getIntent());
        super.onCreate(this,savedInstanceState);

    }


}
