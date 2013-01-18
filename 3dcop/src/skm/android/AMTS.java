package skm.android;

import android.os.Bundle;
import skm.android.ViewModle.ViewModles.amts.AMTSQManager;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 18/11/11
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
public class AMTS extends YesNoActivityBase {


    private static AMTS exam;

    public static AMTS getCurrentInstance(){
       return exam;
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        exam=this;
        modle = AMTSQManager.getInstance(this.getApplicationContext(),getIntent());
        super.onCreate(this,savedInstanceState,R.layout.correct_incorrect);


    }


}
