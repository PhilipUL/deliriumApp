package skm.android;

import android.os.Bundle;
import skm.android.ViewModle.ViewModles.nudesc.NudescManager;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 18/11/11
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
public class Nudesc extends YesNoActivityBase {


    private static Nudesc nudesc;

    public static Nudesc getCurrentInstance(){
       return nudesc;

    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        nudesc =this;
        modle = NudescManager.getInstance(this.getApplicationContext(),getIntent());
        super.onCreate(this,savedInstanceState);

    }


}
