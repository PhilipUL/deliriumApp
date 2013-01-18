package skm.android;

import android.os.Bundle;
import skm.android.ViewModle.ViewModles.CAM.CAMManager;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 18/11/11
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
public class CAM extends YesNoActivityBase {

    private static CAM cam;

    public static CAM getCurrentInstance(){
       return cam;
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        cam =this;
        modle = CAMManager.getInstance(this.getApplicationContext(),getIntent());
        super.onCreate(this,savedInstanceState);

    }


}
