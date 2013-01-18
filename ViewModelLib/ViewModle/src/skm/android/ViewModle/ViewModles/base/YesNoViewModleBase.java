package skm.android.ViewModle.ViewModles.base;

import android.content.Context;
import android.content.Intent;
import gueei.binding.Command;
import gueei.binding.observables.BooleanObservable;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 27/09/11
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class YesNoViewModleBase extends ViewModleBase implements Serializable {

    public YesNoViewModleBase(Context context){
        super(context);
    }


    protected boolean mainMenuLaunched = false;
    protected Intent launcher;
    public Command yes;
    public Command no;
    public Command note;
    public Command help;
    transient public BooleanObservable result = new BooleanObservable(false);

}
