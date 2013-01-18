package skm.android;

import android.os.Bundle;
import skm.android.ViewModle.ViewModles.gds_5.GDS5Manager;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 18/11/11
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
public class GDS5 extends YesNoActivityBase {


    private static GDS5 cam;

    public static GDS5 getCurrentInstance(){
       return cam;

    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        cam =this;
        modle = GDS5Manager.getInstance(this.getApplicationContext(),getIntent());
        super.onCreate(this,savedInstanceState);

    }


}
