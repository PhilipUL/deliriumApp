package skm.android;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 04/11/11
 * Time: 00:13
 * To change this template use File | Settings | File Templates.
 */

import android.os.Bundle;
import gueei.binding.Binder;
import skm.android.ViewModle.ViewModles.DRS.DRSQManager;

public class DRSQuestion extends ActivityBase{

    private static DRSQuestion drsq;



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        drsq=this;
        DRSQManager modle = DRSQManager.getInstance(this.getApplicationContext(), getIntent());

        Binder.init(this.getApplication());
        Binder.setAndBindContentView(this,R.layout.drs, modle);

    }


    public static DRSQuestion getCurrentInstance() {
        return drsq;  //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public void initText() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
