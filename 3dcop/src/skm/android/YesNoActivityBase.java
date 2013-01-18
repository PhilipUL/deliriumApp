package skm.android;

import android.os.Bundle;
import android.view.View;
import gueei.binding.Binder;
import skm.android.ViewModle.ViewModles.base.ViewModelManagerBase;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 18/11/11
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class YesNoActivityBase extends ActivityBase {



     protected ViewModelManagerBase modle;

    /** Called when the activity is first created. */
    public void onCreate(YesNoActivityBase activity,Bundle savedInstanceState){
        onCreate(activity, savedInstanceState, R.layout.yes_no);
    }
    public void onCreate(YesNoActivityBase activity,Bundle savedInstanceState,int layout)
    {
        super.onCreate(savedInstanceState);
        Binder.init(this.getApplication());
        Binder.setAndBindContentView(activity,layout,activity.modle);
        initText();
    }

    public void initText(){
        Vector<View> elements = new Vector<View>();
        elements.add(findViewById(R.id.textview));
        elements.add(findViewById(R.id.Yes));
        elements.add(findViewById(R.id.No));
        elements.add(findViewById(R.id.Explanation));
        initText(elements);
    }


}
